package de.gedoplan;

import com.kumuluz.ee.EeApplication;

public class KumuluzeeMain {

  public static void main(String[] args) {
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");

    EeApplication.main(args);
  }

}
