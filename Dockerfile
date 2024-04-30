FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /build

# Copy pom.xml first to leverage Docker layer caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application
RUN mvn package

FROM openjdk:17

ENV TZ=Asia/Hong_Kong

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /build/target/*.jar app.jar

# Set the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
