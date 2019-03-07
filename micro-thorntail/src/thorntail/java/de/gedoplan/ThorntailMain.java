package de.gedoplan;

import java.net.URL;

import org.wildfly.swarm.Swarm;

// TODO: Auf YAML umstellen
public class ThorntailMain {

  public static void main(String[] args) throws Exception {
    System.setProperty("swarm.context.path", "jee-micro-demo");

    Swarm swarm = new Swarm();

    URL standaloneFullXml = ThorntailMain.class.getClassLoader().getResource("standalone-full.xml");

    swarm
        .withXmlConfig(standaloneFullXml)
        .start()
        .deploy();
  }

}
