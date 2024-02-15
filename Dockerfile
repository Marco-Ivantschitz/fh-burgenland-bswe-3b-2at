FROM openjdk:21 as builder

WORKDIR /

COPY . .

RUN chmod +x ./gradlew && \
    ./gradlew build --no-daemon

FROM openjdk:21

WORKDIR /app

COPY --from=builder build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
