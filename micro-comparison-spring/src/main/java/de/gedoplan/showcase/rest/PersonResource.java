package de.gedoplan.showcase.rest;

import de.gedoplan.showcase.entity.Person;
import de.gedoplan.showcase.persistence.PersonRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/" + PersonResource.PATH)
public class PersonResource {
  public static final String PATH = "person";
  public static final String ID_NAME = "id";
  public static final String ID_TEMPLATE = "{" + ID_NAME + "}";

  @Autowired
  private PersonRepository personRepository;

  @RequestMapping
  public Iterable<Person> getAll() {

    return this.personRepository.findAll();
  }

  @RequestMapping(ID_TEMPLATE)
  public ResponseEntity<Person> get(@PathVariable Integer id) {
    Optional<Person> person = this.personRepository.findById(id);
    if (person.isPresent()) {
      return ResponseEntity.ok(person.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> post(@RequestBody Person person, UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
    Person saved = this.personRepository.save(person);

    URI location = uriComponentsBuilder
      .pathSegment(PATH, saved.getId().toString())
      .build()
      .toUri();

    return ResponseEntity.created(location).body(null);
  }

  @RequestMapping(path = ID_TEMPLATE, method = RequestMethod.DELETE)
  public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
    this.personRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}