from tkinter import Button, Label, Toplevel

def custom_messagebox(title, message):
    msg_box = Toplevel()
    msg_box.title(title)
    msg_box.configure(bg="black")
    msg_box.geometry("400x200")
    msg_box.resizable(False, False)

    msg_box.update_idletasks()
    width = msg_box.winfo_width()
    height = msg_box.winfo_height()
    x = (msg_box.winfo_screenwidth() // 2) - (width // 2)
    y = (msg_box.winfo_screenheight() // 2) - (height // 2)
    msg_box.geometry(f"{width}x{height}+{x}+{y}")

    label = Label(
        msg_box,
        text=message,
        font=("Arial", 14, "bold"),
        fg="red",
        bg="black",
        wraplength=350,
        justify="center"
    )
    label.pack(pady=20)

    close_button = Button(
        msg_box,
        text="YOU ARE INFORMED",
        command=msg_box.destroy,
        font=("Arial", 12),
        fg="white",
        bg="red",
        activebackground="darkred",
        activeforeground="white"
    )
    close_button.pack(pady=10)

    msg_box.transient()
    msg_box.grab_set()
    msg_box.mainloop()