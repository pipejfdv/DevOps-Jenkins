FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/calculadora-ci.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
