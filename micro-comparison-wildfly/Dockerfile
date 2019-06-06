FROM jboss/wildfly:16.0.0.Final

# Add deployment
ADD --chown=jboss:jboss maven/* /opt/jboss/wildfly/standalone/deployments/

USER jboss

# Setup WildFly
# Not needed anymore, since application uses the default datasource
#ADD --chown=jboss:jboss src/main/wildfly/wildfly-setup.cli $HOME
#RUN cd $HOME \
#    && sh $JBOSS_HOME/bin/jboss-cli.sh --file=wildfly-setup.cli \
#    && rm wildfly-setup.cli \
#    && rm -fr $JBOSS_HOME/standalone/configuration/standalone_xml_history
