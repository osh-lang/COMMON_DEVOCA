# syntax=docker/dockerfile:1
FROM eclipse-temurin:17-jdk-jammy as builder

LABEL authors="cheesecat47"

WORKDIR /app

COPY . .

RUN chmod +x gradlew && \
    ./gradlew bootJar

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/build/libs/devoca-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "/app/devoca-0.0.1-SNAPSHOT.jar"]
