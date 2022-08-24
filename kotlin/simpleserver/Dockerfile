FROM openjdk:8
LABEL maintainer="rcantore@gmail.com"

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080

COPY ./build/libs/simpleserver-0.0.1-SNAPSHOT.war simpleserver-0.0.1-SNAPSHOT.war

CMD ["java", "-classpath", "/external-config/", "-jar","simpleserver-0.0.1-SNAPSHOT.war"]


