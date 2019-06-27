package de.gedoplan.showcase.persistence;

import de.gedoplan.showcase.entity.Person;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface PersonRepository extends CrudRepository<Person, Integer> {

  // Nur zur Demo - wird in der Anwendung nicht benutzt
  public List<Person> findByName(String name);
}
