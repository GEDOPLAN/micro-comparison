FROM openjdk:8
#FROM gcr.io/distroless/java

RUN useradd -m micro

USER micro

COPY --chown=micro:micro maven /home/micro

WORKDIR /home/micro

ENTRYPOINT [ "java", "-cp", "classes:lib/*", "de.gedoplan.KumuluzeeMain" ]

EXPOSE 8080
