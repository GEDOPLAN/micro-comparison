package de.gedoplan.showcase.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import de.gedoplan.showcase.entity.Person;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonResourceTest {
  private static final String serverUrl = "http://localhost:8080";
  private static final String serverUrlWebContext = serverUrl;
  private static final String serverUrlRestContext = serverUrlWebContext + "/rs";

  private static Client client;
  private static WebTarget personTarget;

  @BeforeClass
  public static void beforeClass() {
    client = ClientBuilder.newClient();
    personTarget = client.target(serverUrlRestContext + "/person");
  }

  @AfterClass
  public static void afterClass() {
    client.close();
  }

  @Test
  public void test_01_DagobertAndDonalDuckExist() {

    List<Person> persons = personTarget
        .request()
        .accept(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<Person>>() {
        });

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
  public void test_02_PostPutDelete() throws Exception {
    Person person = new Person("Duck", "Tick-" + UUID.randomUUID().toString());

    Response response = personTarget
        .request()
        .post(Entity.json(person));

    assertThat("POST status", response.getStatus(), is(Status.CREATED.getStatusCode()));

    String newPersonUrl = response.getHeaderString(HttpHeaders.LOCATION);
    assertNotNull("New person URL must not be null", newPersonUrl);

    Person personOnServer = client
        .target(newPersonUrl)
        .request(MediaType.APPLICATION_JSON)
        .get(Person.class);

    assertThat("New person name", personOnServer.getName(), is(person.getName()));
    assertThat("New person first name", personOnServer.getFirstname(), is(person.getFirstname()));

    response = client
        .target(newPersonUrl)
        .request()
        .delete();

    assertThat("DELETE status", response.getStatus(), is(Status.NO_CONTENT.getStatusCode()));

    response = client
        .target(newPersonUrl)
        .request(MediaType.APPLICATION_JSON)
        .get();

    assertThat("GET after DELETE status", response.getStatus(), is(Status.NOT_FOUND.getStatusCode()));
  }
}
