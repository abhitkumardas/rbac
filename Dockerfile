FROM openjdk:11

COPY target/*.jar app.jar

ENTRYPOINT java $JVM_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar