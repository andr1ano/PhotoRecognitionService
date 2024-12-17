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

    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/send", new SendImageHandler());
        server.createContext("/request", new RequestImageHandler());
        server.setExecutor(null);
        System.out.println("Server is listening on port " + port);
        server.start();
    }

    static class SendImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            InputStream inputStream = exchange.getRequestBody();
            String imageName = exchange.getRequestURI().getQuery();  // Assuming the image name is passed as a query parameter

            byte[] imageBytes = inputStream.readAllBytes();
            boolean isValid = checkFaceDetection(imageBytes);

            boolean isSaved = false;
            if(isValid)
            {
                byte[] decodedImage = Base64.getDecoder().decode(imageBytes);
                isSaved = saveImageToDatabase(imageName, decodedImage);
            }

            String response = (isValid && isSaved) ? "SUCCESS" : "FAILURE";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class RequestImageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String imageName = exchange.getRequestURI().getQuery();
            byte[] imageData = getImageFromDatabase(imageName);

            if (imageData != null) {
                exchange.getResponseHeaders().set("Content-Type", "image/png");
                exchange.sendResponseHeaders(200, imageData.length);
                OutputStream os = exchange.getResponseBody();
                os.write(imageData);
                os.close();
            } else {
                String response = "Image not found";
                exchange.sendResponseHeaders(404, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    private static byte[] getImageFromDatabase(String imageName) {
        String url = "jdbc:sqlite:photos.db";
        String selectSQL = "SELECT photo_data FROM Photos WHERE photo_name = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setString(1, imageName);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBytes("photo_data");
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return null;
        }
    }

    private static boolean saveImageToDatabase(String imageName, byte[] imageBytes) {
        String url = "jdbc:sqlite:photos.db";
        String insertSQL = "INSERT INTO Photos (photo_name, photo_data) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, imageName);
            pstmt.setBytes(2, imageBytes);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    private static boolean checkFaceDetection(byte[] imageBytes) {
        // Create a channel to connect to the gRPC server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5678)
                .usePlaintext()
                .build();

        // Create a stub to call the gRPC service
        FaceDetectionServiceGrpc.FaceDetectionServiceBlockingStub stub = FaceDetectionServiceGrpc.newBlockingStub(channel);

        // Convert image bytes to Base64 string
        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

        ImageRequest request = ImageRequest.newBuilder().setImageData(ByteString.copyFrom(Base64.getDecoder().decode(encodedImage))).build();

        DetectionResponse response = stub.detectFace(request);

        channel.shutdownNow();

        // Return true if the response indicates a valid face
        return response.getIsValid();
    }
}