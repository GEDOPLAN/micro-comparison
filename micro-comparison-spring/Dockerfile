FROM adoptopenjdk:8-jre-hotspot
#FROM adoptopenjdk:8-jre-openj9

RUN useradd -m micro

USER micro

COPY --chown=micro:micro maven /home/micro

WORKDIR /home/micro

ENTRYPOINT [ "java", "-cp", "/home/micro/*", "de.gedoplan.showcase.SpringMain" ]
#ENTRYPOINT [ "java", "-jar", "/home/micro/micro-comparison-spring.jar" ]

EXPOSE 8080
