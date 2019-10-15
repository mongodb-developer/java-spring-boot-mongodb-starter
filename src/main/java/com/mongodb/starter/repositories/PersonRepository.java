package com.mongodb.starter.repositories;

import com.mongodb.starter.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {

    Person save(Person person);

    List<Person> saveAll(List<Person> persons);

    List<Person> findAll();

    List<Person> findAll(List<String> ids);

    Person findOne(String id);

    long count();

    long delete(String id);

    long delete(List<String> ids);

    long deleteAll();

    Person update(Person person);

    long update(List<Person> persons);

    double getAverageAge();

}
