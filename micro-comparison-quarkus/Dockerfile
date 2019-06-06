FROM openjdk:8
#FROM gcr.io/distroless/java

RUN useradd -m micro

USER micro

COPY --chown=micro:micro maven /home/micro

WORKDIR /home/micro

ENTRYPOINT [ "java", "-jar", "${project.artifactId}-runner.jar" ]

EXPOSE 8080
