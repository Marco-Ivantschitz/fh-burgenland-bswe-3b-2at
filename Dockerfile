FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY examination-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "application.jar"]
