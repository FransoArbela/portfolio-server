# Use a lightweight JDK base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy files
COPY . .

# Make mvnw executable, then build
RUN chmod +x mvnw && ./mvnw package

# Run the app
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
