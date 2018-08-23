package com.mongodb.starter.repositories;

import com.mongodb.starter.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {

    Person save(Person person);

    List<Person> findAll();
}
