package de.gedoplan.showcase.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import de.gedoplan.showcase.entity.Person;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

//import io.restassured.mapper.TypeRef;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonResourceTest {

  @LocalServerPort
  protected int serverPort;

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  public void test_01_DagobertAndDonalDuckExist() {
    List<Person> persons = restTemplate
        .exchange("/person", HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {})
        .getBody();

    boolean foundDagobert = false;
    boolean foundDonald = false;

    for (Person person : persons) {
      if ("Duck".equals(person.getName())) {
        if ("Dagobert".equals(person.getFirstname())) {
          foundDagobert = true;
        }
        if ("Donald".equals(person.getFirstname())) {
          foundDonald = true;
        }
      }
    }

    assertTrue("Dagobert not found", foundDagobert);
    assertTrue("Donald not found", foundDonald);
  }

  @Test
  public void test_02_PostGetDelete() throws Exception {
    Person person = new Person("Duck", "Tick-" + UUID.randomUUID().toString());

    ResponseEntity<?> response = restTemplate.postForEntity("/person", person, Void.class);
    assertThat("POST status", response.getStatusCode(), is(HttpStatus.CREATED));
    URI newPersonUrl = response.getHeaders().getLocation();
    assertNotNull("New person URL must not be null", newPersonUrl);

    Person personOnServer = restTemplate.getForEntity(newPersonUrl, Person.class).getBody();
    assertThat("New person name", personOnServer.getName(), is(person.getName()));
    assertThat("New person first name", personOnServer.getFirstname(), is(person.getFirstname()));

    response = restTemplate.exchange(newPersonUrl, HttpMethod.DELETE, null, Void.class);
    assertThat("DELETE status", response.getStatusCode(), is(HttpStatus.NO_CONTENT));

    response = restTemplate.getForEntity(newPersonUrl, Person.class);
    assertThat("GET after DELETE status", response.getStatusCode(), is(HttpStatus.NOT_FOUND));
  }

}
