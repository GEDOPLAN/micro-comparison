package de.gedoplan.persistence;

import de.gedoplan.entity.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface PersonRepository extends CrudRepository<Person, Integer> {

}
