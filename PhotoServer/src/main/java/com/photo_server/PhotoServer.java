package com.photo_server;

import java.io.*;
import java.net.*;
import java.util.Base64;
import java.sql.*;

import com.google.protobuf.ByteString;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import photo_server.FaceDetectionServiceGrpc;
import photo_server.ServiceServer.ImageRequest;
import photo_server.ServiceServer.DetectionResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PhotoServer {

    public static void main(String[] args)
    {
        int port = 8080;

        //Create and handle our Http server for client-server communication.
        try
        {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("Attempting to start server on port " + port);

            server.createContext("/send", new SendImageHandler());
            server.createContext("/request", new RequestImageHandler());

            server.setExecutor(null);
            server.start();
            System.out.println("Server is listening on port " + port);

        }
        catch (IOException e)
        {
            System.err.println("Error starting the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //POST request impl.
    static class SendImageHandler implements HttpHandler
    {
        @Override
        public void handle(HttpExchange exchange) throws IOException
        {
            InputStream inputStream = exchange.getRequestBody();
            String imageName = exchange.getRequestURI().getQuery();
            byte[] imageBytes = inputStream.readAllBytes();

            boolean isValid = checkFaceDetection(imageBytes);
            boolean isSaved = false;

            String response;
            try
            {
                if (isValid)
                {
                    byte[] decodedImage = Base64.getDecoder().decode(imageBytes);
                    isSaved = saveImageToDatabase(imageName, decodedImage);
                }

                response = (isValid && isSaved) ? "SUCCESS" : "FAILURE";
            }
            catch (Exception e)
            {
                response = "Error processing image: " + e.getMessage();
                System.err.println("Error in SendImageHandler: " + e.getMessage());
            }

            try
            {
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
            catch (IOException e)
            {
                System.err.println("Error sending response: " + e.getMessage());
            }
        }
    }

    //GET request impl.
    static class RequestImageHandler implements HttpHandler
    {
        @Override
        public void handle(HttpExchange exchange) throws IOException
        {
            String imageName = exchange.getRequestURI().getQuery();
            byte[] imageData = getImageFromDatabase(imageName);

            if (imageData != null)
            {
                exchange.getResponseHeaders().set("Content-Type", "image/png");
                exchange.sendResponseHeaders(200, imageData.length);
                OutputStream os = exchange.getResponseBody();
                os.write(imageData);
                os.close();
            }
            else
            {
                String response = "Image not found";
                exchange.sendResponseHeaders(404, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    //Reading data from embedded sqlite db.
    private static byte[] getImageFromDatabase(String imageName)
    {
        String url = "jdbc:sqlite:photos.db";
        String selectSQL = "SELECT photo_data FROM Photos WHERE photo_name = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL))
        {
            pstmt.setString(1, imageName);

            try (ResultSet rs = pstmt.executeQuery())
            {
                if (rs.next())
                {
                    return rs.getBytes("photo_data");
                }
                else
                {
                    return null;
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Database error: " + e.getMessage());
            return null;
        }
    }

    //Writing data from embedded sqlite db only if service found face.
    private static boolean saveImageToDatabase(String imageName, byte[] imageBytes)
    {
        String url = "jdbc:sqlite:photos.db";
        String insertSQL = "INSERT INTO Photos (photo_name, photo_data) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL))
        {
            pstmt.setString(1, imageName);
            pstmt.setBytes(2, imageBytes);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e)
        {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    //Communication with service via gRPC.
    private static boolean checkFaceDetection(byte[] imageBytes)
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("facedetectionservice", 5678).usePlaintext().build();
        FaceDetectionServiceGrpc.FaceDetectionServiceBlockingStub stub = FaceDetectionServiceGrpc.newBlockingStub(channel);

        try
        {
            ImageRequest request = ImageRequest.newBuilder().setImageData(ByteString.copyFrom(imageBytes)).build();
            DetectionResponse response = stub.detectFace(request);
            System.out.println("Face detection response: " + response.getIsValid());
            return response.getIsValid();
        }
        catch (Exception e)
        {
            System.out.println("Error during face detection: " + e.getMessage());
            return false;
        }
        finally
        {
            channel.shutdownNow();
        }
    }
}