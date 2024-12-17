FROM openjdk:17-jdk-slim

# Install Maven to build the project
RUN apt-get update && apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY ./PhotoServer/pom.xml /app/pom.xml

# Copy the source files
COPY ./PhotoServer/src /app/src

# Copy the lib and target directories (if needed)
COPY ./PhotoServer/lib /app/lib
COPY ./PhotoServer/target /app/target

# Build the project using Maven
RUN mvn clean install

# Expose the port your app will run on
EXPOSE 8080

# Set the default command to run the application
CMD ["java", "-jar", "target/PhotoServer-1.0.jar"]