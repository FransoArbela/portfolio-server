# Use a lightweight JDK base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the application
RUN ./mvnw package

# Run the JAR
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
