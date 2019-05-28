package de.gedoplan.showcase;

import java.io.File;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Filter;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.BeforeClass;

public class TestBase {

  static final String deploymentUnitName = UUID.randomUUID().toString();

  protected Log log = LogFactory.getLog(getClass());

  @BeforeClass
  public static void initLogging() {
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
  }

  @Deployment(testable = false)
  public static WebArchive createDeployment() {
    WebArchive archive = ShrinkWrap.create(WebArchive.class, deploymentUnitName + ".war");

    // Add main classes
    Filter<ArchivePath> excludeTestClasses = Filters.exclude(".*Test.*\\.class");
    archive.addPackages(true, excludeTestClasses, "de.gedoplan.showcase");

    // Add main resources
    archive.addAsResource("META-INF/beans.xml");
    archive.addAsResource("META-INF/persistence.xml");

    // Add runtime dependencies
    File[] dependencies = Maven
        .configureResolver()
        .workOffline()
        .loadPomFromFile("pom.xml")
        .importRuntimeDependencies()
        .resolve()
        .withTransitivity()
        .asFile();
    archive.addAsLibraries(dependencies);

    // System.out.println(archive.toString(true));

    return archive;
  }

}
