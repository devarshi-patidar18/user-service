# 1. Use OpenJDK base image
FROM openjdk:21-jdk-slim

# 2. Set working directory in container
WORKDIR /app

# 3. Copy the jar file from target folder into the container
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Expose the port your Spring Boot app runs on
EXPOSE 8080

# 5. Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
