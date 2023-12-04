FROM openjdk:17-alpine
WORKDIR /app
COPY target/restaurant-api-0.0.1-SNAPSHOT.jar restaurant.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","restaurant.jar"]
