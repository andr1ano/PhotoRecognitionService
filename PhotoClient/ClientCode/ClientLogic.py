import requests
import base64
from tkinter import Tk, filedialog
import cv2

from InfoWindow import custom_messagebox

def select_file():
    root = Tk()
    root.withdraw()
    file_path = filedialog.askopenfilename()
    return file_path

# WON'T WORK INSIDE DOCKER!!!
def capture_photo():
    cap = cv2.VideoCapture(0)
    if not cap.isOpened():
        custom_messagebox("CRITICAL ERROR", "Everyone should have at least one video recording unit.")
        return None

    ret, frame = cap.read()
    if ret:
        file_path = "captured_image.png"
        cv2.imwrite(file_path, frame)
        cap.release()
        cv2.destroyAllWindows()
        return file_path
    else:
        cap.release()
        cv2.destroyAllWindows()
        custom_messagebox("CRITICAL ERROR", "Don't you want to be captured?")
        return None

# Generate POST request.
def send_image(file_path, image_name, host='http://photo_server', port=8080):
    url = f"{host}:{port}/send?{image_name}"

    with open(file_path, "rb") as image_file:
        encoded_image = base64.b64encode(image_file.read())
        response = requests.post(url, data=encoded_image)

    print(f"Server response: {response.text}")
    return response.text

# Generate GET request.
def send_image_name(image_name, host='http://photo_server', port=8080):
    url = f"{host}:{port}/request?{image_name}"
    response = requests.get(url)

    if response.status_code == 200:
        with open(f"received_{image_name}.png", 'wb') as f:
            f.write(response.content)
        print(f"Image '{image_name}' received and saved!")
    else:
        print(f"Error: {response.text}")
        custom_messagebox("CRITICAL ERROR", f"What are you plotting with that?")