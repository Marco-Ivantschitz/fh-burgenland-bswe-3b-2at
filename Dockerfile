FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY build/libs/*.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "build/libs/examination-0.0.1-SNAPSHOT.jar"]
