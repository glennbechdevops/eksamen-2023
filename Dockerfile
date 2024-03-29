FROM maven:3.6-jdk-11 as byggmesterbob
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn --no-transfer-progress package

FROM adoptopenjdk/openjdk11:alpine-slim
COPY --from=byggmesterbob /app/target/s3rekognition-0.0.1-SNAPSHOT.jar /app/application.jar
ENTRYPOINT ["java","-jar","/app/application.jar"]