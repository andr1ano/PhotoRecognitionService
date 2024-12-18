FROM openjdk:17-jdk-slim

# Get maven for build.
RUN apt-get update && apt-get install -y maven

# Create dir and copy all working files.
WORKDIR /app

COPY ./PhotoServer/pom.xml /app/pom.xml

COPY ./PhotoServer/src /app/src

COPY ./PhotoServer/lib /app/lib
COPY ./PhotoServer/target /app/target

# Build jar file with all dependencies.
RUN mvn clean install

EXPOSE 8080

# Run server.
CMD ["java", "-jar", "target/PhotoServer-1.0.jar"]