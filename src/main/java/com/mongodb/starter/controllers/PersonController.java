package com.mongodb.starter.controllers;

import com.mongodb.starter.models.Person;
import com.mongodb.starter.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("person")
    public ResponseEntity<Person> postPerson(@RequestBody Person person) {
        Person personSaved = personRepository.save(person);
        return ResponseEntity.ok(personSaved);
    }

    @PostMapping("persons")
    public ResponseEntity<List<Person>> postPersons(@RequestBody List<Person> persons) {
        List<Person> personsSaved = personRepository.saveAll(persons);
        return ResponseEntity.ok(personsSaved);
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable String id) {
        Person person = personRepository.findOne(id);
        if (person == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(person);
    }

    @GetMapping("persons/{ids}")
    public ResponseEntity<List<Person>> getPersons(@PathVariable String ids) {
        List<String> listIds = Arrays.asList(ids.split(","));
        List<Person> persons = personRepository.findAll(listIds);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("persons/count")
    public ResponseEntity<Long> getCount() {
        Long count = personRepository.count();
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<Long> deletePerson(@PathVariable String id) {
        long nbPersonDeleted = personRepository.delete(id);
        return ResponseEntity.ok(nbPersonDeleted);
    }

    @DeleteMapping("persons/{ids}")
    public ResponseEntity<Long> deletePersons(@PathVariable String ids) {
        List<String> listIds = Arrays.asList(ids.split(","));
        long nbPersonDeleted = personRepository.delete(listIds);
        return ResponseEntity.ok(nbPersonDeleted);
    }

    @DeleteMapping("persons")
    public ResponseEntity<Long> deletePersons() {
        long nbPersonDeleted = personRepository.deleteAll();
        return ResponseEntity.ok(nbPersonDeleted);
    }

    @PutMapping("person")
    public ResponseEntity<Person> putPerson(@RequestBody Person person) {
        Person personUpdated = personRepository.update(person);
        return ResponseEntity.ok(personUpdated);
    }

    @PutMapping("persons")
    public ResponseEntity<Long> putPerson(@RequestBody List<Person> persons) {
        long nbPersonUpdated = personRepository.update(persons);
        return ResponseEntity.ok(nbPersonUpdated);
    }

    @GetMapping("persons/averageAge")
    public ResponseEntity<Double> averageAge() {
        return ResponseEntity.ok(personRepository.getAverageAge());
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException e) {
        logger.error("Internal server error.", e);
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
