package de.gedoplan;

import org.wildfly.swarm.Swarm;

public class ThorntailMain {

  public static void main(String[] args) throws Exception {
    // System.setProperty("swarm.context.path", "/");

    Swarm swarm = new Swarm();

    swarm
        .start()
        .deploy();
  }

}
