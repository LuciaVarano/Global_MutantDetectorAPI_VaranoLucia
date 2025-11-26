LABEL authors="luciaVarano"

# Etapa de construcción
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /workspace/app

# Copiar archivos de Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Dar permisos de ejecución
RUN chmod +x ./gradlew

# Copiar el código fuente
COPY src src

# Construir la aplicación
RUN ./gradlew bootJar --no-daemon

# Etapa de ejecución
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el JAR construido
COPY --from=build /workspace/app/build/libs/*.jar app.jar

EXPOSE 8080

# Configurar la memoria JVM y ejecutar
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "app.jar"]