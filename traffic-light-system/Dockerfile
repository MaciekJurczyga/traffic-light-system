FROM gradle:8.5-jdk17-alpine AS build

WORKDIR /project

COPY . .

RUN gradle :web:bootJar --no-daemon -x test

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /project/web/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
