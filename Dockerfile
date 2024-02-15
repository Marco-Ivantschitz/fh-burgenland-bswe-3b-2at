FROM openjdk:21 as build
 
WORKDIR /app
 
COPY / /app
 
RUN ./gradlew bootJar
 
FROM openjdk:21 as run
 
WORKDIR /app
 
COPY --from=build /app/build/libs/*.jar /app/app.jar
 
EXPOSE 8080
 
ENTRYPOINT ["java", "-jar", "app.jar"]
