FROM ubuntu:latest
LABEL authors="luciaVarano"

ENTRYPOINT ["top", "-b"]

FROM alpine:latest as build


RUN apk update
RUN apk add openjdk21


COPY . .


RUN chmod +x ./gradlew


RUN ./gradlew bootJar --no-daemon


FROM eclipse-temurin:21-jre-alpine


EXPOSE 8080


COPY --from=build ./build/libs/mutanteGlobal-0.0.1-SNAPSHOT-plain.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar","-Xms256m", "-jar", "app.jar"]