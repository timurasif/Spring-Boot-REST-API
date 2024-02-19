FROM openjdk:17-alpine

EXPOSE 8080

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

CMD ["java", "-jar", "demo.jar"]
