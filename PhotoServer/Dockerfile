# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the source files
COPY ./PhotoServer /app

# Compile the Java application
RUN javac /app/src/main/java/com/photo_server/* -d /app/bin

EXPOSE 8080

# Set the default command to run the application
CMD ["java", "-cp", "/app/bin:/app/lib/sqlite-jdbc-3.36.0.3.jar", "src/main/java/com/photo_server/PhotoServer.java"]