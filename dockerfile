FROM alpine:3.8

RUN apk add openjdk8-jre

COPY target/tenniskoenig-1.0.0.jar /home/tenniskoenig/tenniskoenig.jar

WORKDIR /home/tenniskoenig

VOLUME /home/tenniskoenig/config/application.yml

EXPOSE 8080

CMD ["java", "-jar", "tenniskoenig.jar"]