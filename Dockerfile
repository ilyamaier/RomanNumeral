FROM openjdk:11
ARG JAR_FILE=build/libs/RomanNumeral-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]