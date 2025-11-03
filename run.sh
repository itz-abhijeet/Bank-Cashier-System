#!/bin/bash

# A helper script to build and run the Bank App Docker container with GUI support.

IMAGE_NAME="bank-app"

# --- Build the Docker Image ---
echo "Building the Docker image: $IMAGE_NAME..."
docker build -t $IMAGE_NAME .

# Check if the build was successful
if [ $? -ne 0 ]; then
    echo "Docker build failed. Please check the Dockerfile and ensure Docker is running."
    exit 1
fi

echo "Build complete."
echo ""

# --- Configure Display for GUI Forwarding ---
# This section sets up the DISPLAY environment variable so the container can
# connect to the host's display.

# Detect the Operating System
OS="$(uname -s)"
DISPLAY_HOST=""

case "$OS" in
    Linux*)
        # On Linux, the DISPLAY variable is usually already set.
        # We can also connect directly to the X11 socket.
        DISPLAY_VAR="$DISPLAY"
        X11_SOCKET="-v /tmp/.X11-unix:/tmp/.X11-unix"
        ;;
    Darwin*)
        # On macOS, we need to use the IP address of the host machine.
        # XQuartz must be installed and configured to allow network connections.
        # The IP address is typically associated with the en0 interface.
        IP=$(ifconfig en0 | grep inet | awk '$1=="inet" {print $2}')
        if [ -z "$IP" ]; then
            echo "Could not find IP address for en0. Make sure you are connected to a network."
            exit 1
        fi
        DISPLAY_VAR="$IP:0"
        # Allow the connection from the local machine
        xhost + "$IP"
        ;;
    *)
        echo "Unsupported operating system: $OS"
        echo "For Windows, please run the Docker command manually with your machine's IP address."
        exit 1
        ;;
esac

echo "Detected OS: $OS"
echo "Setting DISPLAY to: $DISPLAY_VAR"
echo ""

# --- Run the Docker Container ---
echo "Starting the application..."
echo "If the window does not appear, please check your X11 server (XQuartz on Mac) settings."

docker run \
    --rm \
    -e DISPLAY="$DISPLAY_VAR" \
    $X11_SOCKET \
    "$IMAGE_NAME"

echo "Application has closed."
