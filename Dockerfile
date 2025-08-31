# # Stage 1: Build the application
# FROM maven:3.9.6-eclipse-temurin-17 AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn clean package -DskipTests

# # Stage 2: Run the application
# FROM eclipse-temurin:17-jdk
# WORKDIR /app
# COPY --from=build /app/target/*.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "app.jar"]



# Use an official JDK runtime as base image
FROM openjdk:17-jdk-slim
# Set working directory
WORKDIR /app
# Copy JAR file into the container
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar
# Expose port (same as in your Spring Boot app)
EXPOSE 8080
# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]

