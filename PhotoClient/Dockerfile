# Use an official Python base image
FROM python:3.12-slim

# Set the working directory inside the container
WORKDIR /app

# Install system dependencies
RUN apt-get update && apt-get install -y \
    libgl1-mesa-glx \
    libglib2.0-0 \
    tk \
    x11-apps \
    && rm -rf /var/lib/apt/lists/*

# Copy project files into the container
COPY ./PhotoClient /app

# Install Python dependencies
RUN pip install --no-cache-dir -r /app/requirements.txt

# Set the default command to run the GUI application
CMD ["python", "MainGUI.py"]
