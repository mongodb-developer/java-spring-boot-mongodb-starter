package com.mongodb.starter.controllers;

import com.mongodb.starter.models.Person;
import com.mongodb.starter.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person postPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PostMapping("persons")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Person> postPersons(@RequestBody List<Person> persons) {
        return personRepository.saveAll(persons);
    }

    @GetMapping("persons")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable String id) {
        Person person = personRepository.findOne(id);
        if (person == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(person);
    }

    @GetMapping("persons/{ids}")
    public List<Person> getPersons(@PathVariable String ids) {
        List<String> listIds = asList(ids.split(","));
        return personRepository.findAll(listIds);
    }

    @GetMapping("persons/count")
    public Long getCount() {
        return personRepository.count();
    }

    @DeleteMapping("person/{id}")
    public Long deletePerson(@PathVariable String id) {
        return personRepository.delete(id);
    }

    @DeleteMapping("persons/{ids}")
    public Long deletePersons(@PathVariable String ids) {
        List<String> listIds = asList(ids.split(","));
        return personRepository.delete(listIds);
    }

    @DeleteMapping("persons")
    public Long deletePersons() {
        return personRepository.deleteAll();
    }

    @PutMapping("person")
    public Person putPerson(@RequestBody Person person) {
        return personRepository.update(person);
    }

    @PutMapping("persons")
    public Long putPerson(@RequestBody List<Person> persons) {
        return personRepository.update(persons);
    }

    @GetMapping("persons/averageAge")
    public Double averageAge() {
        return personRepository.getAverageAge();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}
