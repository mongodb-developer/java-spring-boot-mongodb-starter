package com.mongodb.starter;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.starter.models.Person;
import com.mongodb.starter.repositories.PersonRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIT {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TestHelper testHelper;
    private String URL;

    @Autowired
    PersonControllerIT(MongoClient mongoClient) {
        createPersonCollectionIfNotPresent(mongoClient);
    }

    @PostConstruct
    void setUp() {
        URL = "http://localhost:" + port + "/api";
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @DisplayName("POST /person with 1 person")
    @Test
    void postPerson() {
        // GIVEN
        // WHEN
        ResponseEntity<Person> result = rest.postForEntity(URL + "/person", testHelper.getMax(), Person.class);
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Person personResult = result.getBody();
        assertThat(personResult.getId()).isNotNull();
        assertThat(personResult).isEqualToIgnoringGivenFields(testHelper.getMax(), "id", "createdAt");
    }

    @DisplayName("POST /persons with 2 person")
    @Test
    void postPersons() {
        // GIVEN
        // WHEN
        HttpEntity<List<Person>> body = new HttpEntity<>(testHelper.getListMaxAlex());
        ResponseEntity<List<Person>> response = rest.exchange(URL + "/persons", HttpMethod.
                POST, body, new ParameterizedTypeReference<List<Person>>() {
        });
        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).usingElementComparatorIgnoringFields("id", "createdAt")
                                      .containsExactlyInAnyOrderElementsOf(testHelper.getListMaxAlex());
    }

    @DisplayName("GET /persons with 2 persons")
    @Test
    void getPersons() {
        // GIVEN
        List<Person> personsInserted = personRepository.saveAll(testHelper.getListMaxAlex());
        // WHEN
        ResponseEntity<List<Person>> result = rest.exchange(URL + "/persons", HttpMethod.GET, null,
                                                            new ParameterizedTypeReference<List<Person>>() {
                                                            });
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).containsExactlyInAnyOrderElementsOf(personsInserted);
    }

    @DisplayName("GET /person/{id}")
    @Test
    void getPersonById() {
        // GIVEN
        Person personInserted = personRepository.save(testHelper.getAlex());
        ObjectId idInserted = personInserted.getId();
        // WHEN
        ResponseEntity<Person> result = rest.getForEntity(URL + "/person/" + idInserted, Person.class);
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(personInserted);
    }

    @DisplayName("GET /persons/{ids}")
    @Test
    void getPersonsByIds() {
        // GIVEN
        List<Person> personsInserted = personRepository.saveAll(testHelper.getListMaxAlex());
        List<String> idsInserted = personsInserted.stream().map(Person::getId).map(ObjectId::toString).collect(toList());
        // WHEN
        String url = URL + "/persons/" + String.join(",", idsInserted);
        ResponseEntity<List<Person>> result = rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
        });
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).containsExactlyInAnyOrderElementsOf(personsInserted);
    }

    @DisplayName("GET /persons/count")
    @Test
    void getCount() {
        // GIVEN
        personRepository.saveAll(testHelper.getListMaxAlex());
        // WHEN
        ResponseEntity<Long> result = rest.getForEntity(URL + "/persons/count", Long.class);
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(2L);
    }

    @DisplayName("DELETE /person/{id}")
    @Test
    void deletePersonById() {
        // GIVEN
        Person personInserted = personRepository.save(testHelper.getMax());
        ObjectId idInserted = personInserted.getId();
        // WHEN
        ResponseEntity<Long> result = rest.exchange(URL + "/person/" + idInserted.toString(), HttpMethod.DELETE, null,
                                                    new ParameterizedTypeReference<Long>() {
                                                    });
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(1L);
        assertThat(personRepository.count()).isEqualTo(0L);
    }

    @DisplayName("DELETE /persons/{ids}")
    @Test
    void deletePersonsByIds() {
        // GIVEN
        List<Person> personsInserted = personRepository.saveAll(testHelper.getListMaxAlex());
        List<String> idsInserted = personsInserted.stream().map(Person::getId).map(ObjectId::toString).collect(toList());
        // WHEN
        ResponseEntity<Long> result = rest.exchange(URL + "/persons/" + String.join(",", idsInserted), HttpMethod.DELETE, null,
                                                    new ParameterizedTypeReference<Long>() {
                                                    });
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(2L);
        assertThat(personRepository.count()).isEqualTo(0L);
    }

    @DisplayName("DELETE /persons")
    @Test
    void deletePersons() {
        // GIVEN
        personRepository.saveAll(testHelper.getListMaxAlex());
        // WHEN
        ResponseEntity<Long> result = rest.exchange(URL + "/persons", HttpMethod.DELETE, null,
                                                    new ParameterizedTypeReference<Long>() {
                                                    });
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(2L);
        assertThat(personRepository.count()).isEqualTo(0L);
    }

    @DisplayName("PUT /person")
    @Test
    void putPerson() {
        // GIVEN
        Person personInserted = personRepository.save(testHelper.getMax());
        // WHEN
        personInserted.setAge(32);
        personInserted.setInsurance(false);
        HttpEntity<Person> body = new HttpEntity<>(personInserted);
        ResponseEntity<Person> result = rest.exchange(URL + "/person", HttpMethod.PUT, body, new ParameterizedTypeReference<Person>() {
        });
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(personRepository.findOne(personInserted.getId().toString()));
        assertThat(result.getBody().getAge()).isEqualTo(32);
        assertThat(result.getBody().getInsurance()).isFalse();
        assertThat(personRepository.count()).isEqualTo(1L);
    }

    @DisplayName("PUT /persons with 2 persons")
    @Test
    void putPersons() {
        // GIVEN
        List<Person> personsInserted = personRepository.saveAll(testHelper.getListMaxAlex());
        // WHEN
        personsInserted.get(0).setAge(32);
        personsInserted.get(0).setInsurance(false);
        personsInserted.get(1).setAge(28);
        personsInserted.get(1).setInsurance(true);
        HttpEntity<List<Person>> body = new HttpEntity<>(personsInserted);
        ResponseEntity<Long> result = rest.exchange(URL + "/persons", HttpMethod.PUT, body, new ParameterizedTypeReference<Long>() {
        });
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(2L);
        Person max = personRepository.findOne(personsInserted.get(0).getId().toString());
        Person alex = personRepository.findOne(personsInserted.get(1).getId().toString());
        assertThat(max.getAge()).isEqualTo(32);
        assertThat(max.getInsurance()).isFalse();
        assertThat(alex.getAge()).isEqualTo(28);
        assertThat(alex.getInsurance()).isTrue();
        assertThat(personRepository.count()).isEqualTo(2L);
    }

    @DisplayName("GET /persons/averageAge")
    @Test
    void getAverageAge() {
        // GIVEN
        personRepository.saveAll(testHelper.getListMaxAlex());
        // WHEN
        ResponseEntity<Long> result = rest.getForEntity(URL + "/persons/averageAge", Long.class);
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(29L);
    }

    private void createPersonCollectionIfNotPresent(MongoClient mongoClient) {
        // This is required because it is not possible to create a new collection within a multi-documents transaction.
        // Some tests start by inserting 2 documents with a transaction.
        MongoDatabase db = mongoClient.getDatabase("test");
        if (!db.listCollectionNames().into(new ArrayList<>()).contains("persons"))
            db.createCollection("persons");
    }
}
