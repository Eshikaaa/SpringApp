# Use an official OpenJDK runtime as the base image
FROM openjdk:21-oracle

# Copy the JAR file into the container
COPY target/*.jar app.jar
EXPOSE 8080
# Set the command to run your JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
