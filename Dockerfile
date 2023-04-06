FROM openjdk:11
ARG JAR_FILE=build/libs/
COPY ${JAR_FILE} Alledrogo_Spring_Lab-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]