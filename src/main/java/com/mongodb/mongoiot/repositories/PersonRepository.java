package com.mongodb.mongoiot.repositories;

import com.mongodb.mongoiot.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {

    Person save(Person person);

    List<Person> findAll();
}
