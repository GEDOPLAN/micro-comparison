package de.gedoplan.showcase.service;

import de.gedoplan.showcase.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ApplicationScoped
public class InitPersonDemoDataService {
  @Inject
  private EntityManager entityManager;

  private static Log log = LogFactory.getLog(InitPersonDemoDataService.class);

  /**
   * Create test/demo data.
   * Attn: Interceptors may not be called, if method is private!
   *
   * @param event
   *          Application scope initialization event
   */
  @Transactional
  void createDemoData(@Observes @Initialized(ApplicationScoped.class) Object event) {
    try {
      if (this.entityManager.createQuery("select count(x) from Person x", Long.class).getSingleResult() == 0) {
        this.entityManager.persist(new Person("Duck", "Dagobert"));
        this.entityManager.persist(new Person("Duck", "Donald"));
      }
    } catch (Exception e) {
      log.warn("Cannot create demo data", e);
    }

  }

}
