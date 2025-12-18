FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR into container
COPY /target/StockSync-0.0.1-SNAPSHOT.jar .

# Expose app port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "StockSync-0.0.1-SNAPSHOT.jar"]

