# Verwende das offizielle OpenJDK 21-Basisimage
FROM adoptopenjdk:21-jdk-hotspot

# Setze das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiere das Build-Artefakt aus dem vorherigen Schritt in den Container
COPY build/libs/*.jar app.jar

# Exponiere den Port 8080, auf dem die Anwendung läuft
EXPOSE 8080

# Befehl zum Ausführen der Anwendung, wenn der Container gestartet wird
CMD ["java", "-jar", "app.jar"]
