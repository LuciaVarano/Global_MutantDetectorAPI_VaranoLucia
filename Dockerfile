FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar archivos necesarios
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Dar permisos y construir
RUN chmod +x ./gradlew && ./gradlew bootJar --no-daemon

EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "/app/build/libs/mutanteGlobal-0.0.1-SNAPSHOT.jar"]