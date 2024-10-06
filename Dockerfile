FROM ubuntu:latest AS build
RUN apt-get update && apt-get install openjdk-17-jdk maven -y
WORKDIR /app
COPY . /app
RUN mvn clean install -DskipTests
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /app/target/curso-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]