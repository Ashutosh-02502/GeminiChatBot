# Build stage
FROM maven:3.8.5-openjdk-17-slim AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src

# Cache dependencies
RUN mvn dependency:go-offline

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy jar from build stage
COPY --from=builder /build/target/Gemini-chat-0.0.1-SNAPSHOT.jar app.jar

# Add metadata
LABEL maintainer="Ashutosh <ashutoshbhardwaj257@gmail.com>"
LABEL version="1.0"
LABEL description="Gemini Chat Backend Service"

# Configure JVM options
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]