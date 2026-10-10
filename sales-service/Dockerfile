FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/sales-service-0.0.1-SNAPSHOT-exec.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]