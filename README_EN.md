# Document face collection application

In this task, you'll implement an application - a server (services) and a client (desktop app) for sending a photo with a close-up face on it. I know you are excited already, so I'll get to details. Note, features marked `*` are optional.

```
Client (language of your choice) <-> Java Server <-> call Python face detection model
                                         ^
                                         |
                                         V
                                    Data base (your pick, can be just a folder with photos + a .csv table)
```

The architecture is fairly simple.

A **client** is a desktop app (terminal app, GUI app - your choice) which loads an image (from disk and `*` from webcam). I recommend choosing Python for this, but I do not insist. Just make sure I can run it easily. Maybe include a README or something like that. This app also has to send this image over a raw socket (or `*` http).

A **Java Server** is a java program which receives a request from a client with a photo and name. Then, it has to (1) validate the photo (see Part 2), (2) add it to the database with the specified name. After that, it sends a "confirmation message" to the client, so that client can print "photo uploaded successfully" message.

A **Data base** is up to you. If you feel adventurous (`*`), pick a nice off-the-shelf database software (sqlite, postgres, etc.). Otherwise, just make a folder somewhere on disk and put photos in it. For names, use ".csv" table mapping names with image file names.

A **Python face detection** is a separate service with which you can also communicate via sockets, or `*` gRPC.  It's a python service which receives an image and returns a bounding box of a face on the picture. 
1. You can assume there is only one face. (`*` return multiple faces if they exist, return error message to client if multiple faces are found)
2. A bounding box is four numbers: (X, Y, W, H). X, Y - coordinates of the top left corner of a rectangle, W, H - width and height of a rectangle.

# Part 1. Upload.

In part 1, implement general client-server communication. Just upload an image and save it to the database w/o validation. Return a success message to the client.

# Part 2. Validate.

In part 2, implement validation. First, implement Python face detection service. Python face detection is implemented for you. See the example of usage.
Then, Java Server must validate the result. The result is valid, if:
1. Bounding box takes over 15% of the area of the photo. If a face is too small it's not good for a document.
2. Face is detected. If Python face detector does not detect anything, the photo is not valid.
3. `*` Only one face is detected. If Python face detector detects more than one face, the photo is not valid.

If the photo is invalid, return error message.
If the photo is valid, add it to the database, return success message.

# Part 3. Query the photo by name.

In part 3, implement a photo request. Client must be able to send a request with a name. Server, looks up the name in the database. If it finds it, the corresponding image must be loaded and sent back to the client. Otherwise, an error message must be sent.


# Part 4. Docker

This part can be done in parallel to the above parts. Java server (+database), a python service, and a client must be put inside separate docker containers. Also `*`, there must be a docker-compose file to start server and a python service.