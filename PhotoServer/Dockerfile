# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the source files
COPY PhotoServer/src /app/src
COPY photos.db /app/photos.db

# Compile the Java application
RUN javac src/main/java/com/photo_server/PhotoServer.java -d /app/bin

# Expose the application and Python service ports
EXPOSE 8080 5678

# Set the default command to run the application
CMD ["java", "-cp", "/app/bin", "com.photo_server.PhotoServer"]