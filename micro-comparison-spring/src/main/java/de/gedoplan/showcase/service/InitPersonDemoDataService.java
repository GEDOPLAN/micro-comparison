package de.gedoplan.showcase.service;

import de.gedoplan.showcase.entity.Person;
import de.gedoplan.showcase.persistence.PersonRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitPersonDemoDataService {

  private static Log log = LogFactory.getLog(InitPersonDemoDataService.class);

  /**
   * Create test/demo data.
   *
   * @param personRepository
   *          repository
   */
  @Bean
  CommandLineRunner createDemoData(PersonRepository personRepository) {
    return args -> {
      try {
        if (personRepository.count() == 0) {
          personRepository.save(new Person("Duck", "Dagobert"));
          personRepository.save(new Person("Duck", "Donald"));
        }
      } catch (Exception e) {
        log.warn("Cannot create demo data", e);
      }
    };
  }
}
