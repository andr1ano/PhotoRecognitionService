# Use an official Python base image
FROM python:3.12-slim

# Set the working directory
WORKDIR /app

# Install necessary system dependencies for OpenCV
RUN apt-get update && apt-get install -y \
    libgl1-mesa-glx \
    libglib2.0-0 \
    && rm -rf /var/lib/apt/lists/*

# Install Python dependencies
COPY ./FaceRecoModule /app

RUN pip install --no-cache-dir -r /app/requirements.txt


# Expose the server port
EXPOSE 5678

# Set the default command to run the server
CMD ["python", "FaceDetectionService.py"]
