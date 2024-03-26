FROM openjdk:17-jdk
LABEL maintainer="junho"
#변수명 설정
ARG JAR_FILE=build/libs/keyAppsk-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} docker-keyappsk.jar
ENTRYPOINT ["java", "-jar", "/docker-keyappsk.jar"]