import socket
import cv2
import numpy as np
import base64

def process_image(image_bytes):
    image_array = np.frombuffer(base64.b64decode(image_bytes), dtype=np.uint8)
    image = cv2.imdecode(image_array, cv2.IMREAD_COLOR)

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    face_cascade = cv2.CascadeClassifier('model/haarcascade_default.xml')
    detections = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5, minSize=(20, 20))

    return len(detections) == 1

def start_server():
    host = 'localhost'
    port = 5678
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((host, port))
    server_socket.listen(5)
    print(f"Face detection service running on {host}:{port}")

    while True:
        client_socket, address = server_socket.accept()
        print(f"Connection from {address}")

        image_data = b""
        while True:
            chunk = client_socket.recv(1024)
            if not chunk:
                break
            image_data += chunk

        is_valid = process_image(image_data)
        response = "true" if is_valid else "false"
        client_socket.sendall(response.encode())
        client_socket.close()

if __name__ == "__main__":
    start_server()
