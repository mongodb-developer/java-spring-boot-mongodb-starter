package com.mongodb.starter;

import com.mongodb.starter.models.Person;
import com.mongodb.starter.repositories.PersonRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIT {

    private static String URL;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TestHelper testHelper;

    @BeforeEach
    void setUp() {
        URL = "http://localhost:" + port + "/api";
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @DisplayName("GET /persons with 2 persons")
    @Test
    void getPersons() {
        // GIVEN
        List<Person> personsInserted = personRepository.saveAll(Arrays.asList(testHelper.getMax(), testHelper.getAlex()));
        // WHEN
        ResponseEntity<List<Person>> result = rest.exchange(URL + "/persons", HttpMethod.GET, null,
                                                            new ParameterizedTypeReference<>() {
                                                            });
        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Person> persons = result.getBody();
        assertThat(persons).hasSize(2);
        List<ObjectId> expectedIds = personsInserted.stream().map(Person::getId).collect(Collectors.toList());
        assertThat(expectedIds).containsExactlyInAnyOrder(persons.get(0).getId(), persons.get(1).getId());
    }

}
