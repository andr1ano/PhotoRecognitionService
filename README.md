# Face Recognition Client-Server Service

This project implements a Client-Server service for face recognition. The Client sends a photo to the Server, which saves it to an SQLite database if the trained model detects exactly one face in the provided photo.

## Overview

### Server

The Server handles communication with both the Client and the Service. It contains the core business logic, including SQL query execution.

#### Structure
```
lib       - Contains required JAR files for SQLite dependency.
src       - Contains Java and .proto source files implementing the server logic.
target    - Compiled files generated via Maven, including gRPC files and a JAR file with all dependencies.
pom.xml   - Maven build file with required dependencies for compilation.
photos.db - SQLite database storing photo entries with name (TEXT) and content (BLOB).
```

#### Functionality
- **Server-Service Logic**
  - Communicates with the Service via gRPC.
  - Connects to the Service at port 5678 in the Docker network.
  - Sends photo binaries and receives a boolean indicating if exactly one face was detected.

- **Server-Client Logic**
  - Communicates with the Client via HTTP.
  - Opens port 8080 in the Docker network.
  - Supports:
    - **POST Requests**: Saves a photo to the database if the "one face" condition is satisfied.
    - **GET Requests**: Retrieves a photo from the database by name and returns it to the Client.

### Service

The Service is a standalone entity that communicates only with the Server over the Docker network (port 5678).

#### Functionality
- Waits for photo input from the Server.
- Uses OpenCV (with the provided Haar Cascade model `model/haarcascade_default.xml`) to detect faces in the photo.
- Returns a boolean indicating if exactly one face was found.

### Client

The Client provides a graphical user interface (GUI) to interact with the Server via HTTP.

#### Structure
```
Photos folder  - Contains test photos:
                 1. `Java.png` - No face (fail case).
                 2. `ManyFaces.jpg` - Multiple faces (fail case).
                 3. `OneOfManyFaces.jpg` - Single face (success case).
               - You can also add there any photos you want to test.

ClientLogic    - Handles HTTP POST (send photo) and GET (retrieve photo by name) requests.
               - Includes functionality to capture photos from a camera (not supported in Docker).

InfoWindow     - Creates a structured and visually unique information window.

MainGUI        - Entry point generating the main menu and handling connections.
```

#### Notes
**Camera Support: Camera functionality will not work when running in Docker.**

### Docker

Each entity has its own folder with a Dockerfile to generate container images with all dependencies included.

#### Running the Application
1. Extract the code to a directory on your machine.
2. Navigate to the directory.
3. Run the following command:
   ```
   docker-compose up --build
   ```

This will build and start three containers (Server, Service, Client). The application will then be ready for use.

## Final Notes

This project satisfies the core requirements for a face recognition service. While functional, there is room for improvement, and some bugs may still exist. Feedback and contributions are welcome!