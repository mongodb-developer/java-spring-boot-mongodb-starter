package com.mongodb.mongoiot.repositories;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.mongoiot.models.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoDBPersonRepository implements PersonRepository {

    private final MongoCollection<Person> personCollection;

    @Autowired
    public MongoDBPersonRepository(MongoClient mongoClient) {
        MongoDatabase db = mongoClient.getDatabase("test");
        personCollection = db.getCollection("sensor", Person.class);
    }

    @Override
    public Person save(Person person) {
        person.setId(new ObjectId());
        personCollection.insertOne(person);
        return person;
    }

    @Override
    public List<Person> findAll() {
        return personCollection.find().into(new ArrayList<>());
    }
}
