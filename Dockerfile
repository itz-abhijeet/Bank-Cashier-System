# Use an official OpenJDK runtime as a parent image
# We are using OpenJDK 11, which has good support for Swing applications.
FROM openjdk:11

# Install essential X11 libraries required for GUI applications to run.
# These libraries allow the Java application inside the container to communicate
# with the X server on the host machine to display the UI.
RUN apt-get update && apt-get install -y \
    xauth \
    libx11-6 \
    libxtst6 \
    libxext6 \
    libxi6 \
    libxrender1

# Set the working directory inside the container.
# All subsequent commands will be run from this directory.
WORKDIR /app

# Copy all the Java source files from the host into the container's working directory.
COPY *.java .

# Compile the Java source code into .class files inside the container.
RUN javac *.java

# Define the command to run the application when the container starts.
# This will execute "java BankApp".
CMD ["java", "BankApp"]
