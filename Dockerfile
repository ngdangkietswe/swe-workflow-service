FROM eclipse-temurin:17.0.8.1_1-jre-jammy
WORKDIR /app
COPY /target/swe-workflow-service.jar swe-workflow-service.jar
ENTRYPOINT ["java", "-jar", "swe-workflow-service.jar"]
EXPOSE 7006 7060