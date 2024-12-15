from tkinter import Entry, Button, Label
from ClientLogic import *

def create_gui():
    def center_window(window):
        window.update_idletasks()
        screen_width = window.winfo_screenwidth()
        screen_height = window.winfo_screenheight()
        size = tuple(int(dim) for dim in window.geometry().split('+')[0].split('x'))
        x = screen_width // 2 - size[0] // 2
        y = screen_height // 2 - size[1] // 2
        window.geometry(f"{size[0]}x{size[1]}+{x}+{y}")

    def on_send_image(from_camera=False):
        image_name = name_entry.get().strip()
        if not image_name:
            custom_messagebox("CRITICAL ERROR", "CONSULT INFORMATION FIELD ON NAMING")
            return

        file_path = capture_photo() if from_camera else select_file()
        if file_path:
            response = send_image(file_path, image_name)
            if response == "SUCCESS":
                result_label.config(text="PROCEDURE SUCCEEDED, YOUR CONTRIBUTION MATTERED MORE THAN YOU THINK", fg="green")
            else:
                result_label.config(text="We recommend you to try again and be more compliant.", fg="red")

    def on_request_image():
        image_name = name_entry.get().strip()
        if not image_name:
            custom_messagebox("CRITICAL ERROR", "CONSULT INFORMATION FIELD ON NAMING")
            return

        send_image_name(image_name)
        result_label.config(text=f"PREEMPTIVE MEASURES SUCCEEDED, INFORMATION PROVIDED", fg="green")

    window = Tk()
    window.title("SURVEY PROGRAM")
    window.geometry("500x400")
    window.configure(bg="black")
    center_window(window)

    title_label = Label(window, text="MASS SURVEILLANCE PROCEDURE", font=("Arial", 16, "bold"), fg="white", bg="black")
    title_label.pack(pady=10)

    Label(window, text="TAKE PHOTO OR SEND ONE FROM YOUR DISK (NAME SHOULD BE PROVIDED):", fg="white", bg="black").pack(pady=10)
    name_entry = Entry(window, width=40)
    name_entry.pack(pady=10)

    send_image_button = Button(window, text="SEND FROM DISK", command=lambda: on_send_image(from_camera=False))
    send_image_button.pack(pady=10)

    capture_image_button = Button(window, text="CAPTURE FROM CAMERA",
                                  command=lambda: on_send_image(from_camera=True))
    capture_image_button.pack(pady=10)

    request_image_button = Button(window, text="REQUEST FROM DATABASE", command=on_request_image)
    request_image_button.pack(pady=10)

    result_label = Label(window, text="", fg="green", bg="black")
    result_label.pack(pady=10)

    window.mainloop()

if __name__ == "__main__":
    create_gui()