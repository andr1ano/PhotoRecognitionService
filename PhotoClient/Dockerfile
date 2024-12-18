FROM python:3.12-slim

# Create directory and copy all working files.
WORKDIR /app

COPY ./PhotoClient /app

# Set folder as the Python module directory.
ENV PYTHONPATH=/app/ClientCode

# Install all required system dependencies.
RUN apt-get update && apt-get install -y \
    libgl1-mesa-glx \
    libglib2.0-0 \
    tk \
    x11-apps \
    && rm -rf /var/lib/apt/lists/*

# Install Python dependencies.
RUN pip install --no-cache-dir -r /app/requirements.txt

# Run client.
CMD ["python", "ClientCode/MainGUI.py"]
