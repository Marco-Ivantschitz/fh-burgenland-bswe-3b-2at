# Use the official OpenJDK image as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the Gradle build output directory to the container
COPY build/libs/*.jar app.jar

# Run the JAR file
CMD ["java", "-jar", "app.jar"]
