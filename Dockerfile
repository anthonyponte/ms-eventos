FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/ms-eventos-1.0.1.jar app.jar
EXPOSE 9090
RUN apt-get update && apt-get install -y curl
ENTRYPOINT ["java", "-jar", "app.jar"]