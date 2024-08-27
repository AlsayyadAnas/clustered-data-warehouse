# Use the official image as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/clustered-data-warehouse.jar /app/clustered-data-warehouse.jar

EXPOSE 8080
# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/clustered-data-warehouse.jar"]