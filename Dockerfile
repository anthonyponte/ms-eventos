FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/ms-eventos-1.0.2.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]