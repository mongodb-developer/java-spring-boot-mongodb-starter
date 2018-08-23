package com.mongodb.mongoiot.controllers;

import com.mongodb.mongoiot.models.Person;
import com.mongodb.mongoiot.repositories.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("person")
    public ResponseEntity<Person> postPerson(@RequestBody Person person) {
        Person saved = personRepository.save(person);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> people = personRepository.findAll();
        return ResponseEntity.ok(people);
    }
}
