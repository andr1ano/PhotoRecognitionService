FROM python:3.12-slim

# Create directory and copy all working files.
WORKDIR /app

COPY ./FaceRecoModule /app

# Set folder as the Python module directory.
ENV PYTHONPATH=/app/ServiceCode

# Install all required system dependencies.
RUN apt-get update && apt-get install -y \
    libgl1-mesa-glx \
    libglib2.0-0 \
    && rm -rf /var/lib/apt/lists/*

# Install Python dependencies.
RUN pip install --no-cache-dir -r /app/requirements.txt

EXPOSE 5678

# Run service.
CMD ["python", "ServiceCode/FaceDetectionService.py"]
