# Etapa 1: Compilación
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
# Descargamos dependencias primero para aprovechar el cache de Docker layers
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos solo el ejecutable (evitando el .original)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]