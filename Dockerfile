FROM openjdk:11-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} gcms.jar

COPY bootstrap /var/runtime/bootstrap
RUN chmod 755 /var/runtime/bootstrap

WORKDIR /var/task

CMD ["java", "-jar", "/gcms.jar"]
