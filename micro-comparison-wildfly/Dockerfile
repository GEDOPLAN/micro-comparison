FROM lorislab/galleon-wildfly:16.0.0.Final as buildhelper

RUN galleon install wildfly:current#${WILDFLY_VERSION} --layers=jaxrs,cdi,jpa,h2-database --dir=wildfly

FROM adoptopenjdk:8-jre-hotspot

RUN useradd -m micro

USER micro

COPY --from=buildhelper --chown=micro:micro /opt/jboss/wildfly /home/micro/wildfly
COPY --chown=micro:micro maven/* /home/micro/wildfly/standalone/deployments

WORKDIR /home/micro

ENTRYPOINT ["/home/micro/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

EXPOSE 8080