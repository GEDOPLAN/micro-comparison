FROM openjdk:8-jre-alpine

ADD ./target/micro-thorntail-hollow-thorntail.jar /opt/
ADD ./target/micro-thorntail.war /opt/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/micro-thorntail-hollow-thorntail.jar", "-Djava.net.preferIPv4Stack=true", "/opt/micro-thorntail.war"]