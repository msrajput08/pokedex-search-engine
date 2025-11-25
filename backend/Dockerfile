# Step 1: Build the Spring Boot app
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .           # pom.xml same folder me hona chahiye
COPY src ./src           # src folder bhi same folder me hona chahiye
RUN mvn clean package -DskipTests

# Step 2: Run the Spring Boot app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
