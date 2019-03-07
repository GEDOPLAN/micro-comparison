FROM jboss/wildfly:16.0.0.Final

USER jboss

# Configure server
COPY --chown=jboss:jboss src/main/wildfly/wildfly-setup.cli /opt/jboss/wildfly-setup/
RUN cd /opt/jboss/wildfly-setup \
    && sh /opt/jboss/wildfly/bin/jboss-cli.sh --file=wildfly-setup.cli \
    && cd .. \
    && rm -fr wildfly-setup \
    && rm -fr /opt/jboss/wildfly/standalone/configuration/standalone_xml_history 

# Add deployment
ADD maven/* /opt/jboss/wildfly/standalone/deployments/

#ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
#CMD []