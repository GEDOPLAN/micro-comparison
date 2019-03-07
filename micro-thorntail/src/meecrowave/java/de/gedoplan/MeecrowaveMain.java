package de.gedoplan;

import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.meecrowave.Meecrowave;

public class MeecrowaveMain {
  public static void main(String[] args) {

    // JUL in Log4j2 leiten; Achtung: Property muss vor jeglicher Log-Aktivität gesetzt werden!
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");

    Log log = LogFactory.getLog(MeecrowaveMain.class);

    try (Meecrowave meecrowave = new Meecrowave().bake()) {
      log.info("started");

      new Semaphore(-1).acquire();
    } catch (Exception e) {

      // TODO Meecrowave scheint die Logger beim Shutdown zu schliessen
      if (log.isErrorEnabled()) {
        log.error("Kann Fahrstrassen-Service nicht starten", e);
      } else {
        System.err.println("Kann Fahrstrassen-Service nicht starten: " + e);
      }
    }

    System.exit(0);
  }

}
