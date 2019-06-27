package de.gedoplan.showcase.persistence;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.showcase.entity.Person;
import de.gedoplan.showcase.entity.Person_;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class PersonRepository extends SingleIdEntityRepository<Integer, Person> {

  // Nur zur Demo - wird in der Anwendung nicht benutzt
  public List<Person> findByName(String name) {
    return findMultiByProperty(Person_.name, name);
  }
}
