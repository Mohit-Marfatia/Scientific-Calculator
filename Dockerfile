FROM openjdk:17-jdk-slim

WORKDIR /app

COPY ScientificCalculator/target/ScientificCalculator-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["sh", "-c", "sleep infinity"]

CMD ["java", "-jar", "app.jar"]
