package com.mongodb.starter.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.WriteModel;
import com.mongodb.starter.models.Person;
import org.bson.BsonDocument;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

@Repository
public class MongoDBPersonRepository implements PersonRepository {

    private final MongoCollection<Person> personCollection;

    @Autowired
    public MongoDBPersonRepository(MongoClient mongoClient) {
        MongoDatabase db = mongoClient.getDatabase("test");
        personCollection = db.getCollection("person", Person.class);
    }

    @Override
    public Person save(Person person) {
        person.setId(new ObjectId());
        personCollection.insertOne(person);
        return person;
    }

    @Override
    public List<Person> saveAll(List<Person> persons) {
        persons.forEach(p -> p.setId(new ObjectId()));
        personCollection.insertMany(persons);
        return persons;
    }

    @Override
    public List<Person> findAll() {
        return personCollection.find().into(new ArrayList<>());
    }

    @Override
    public List<Person> findAll(List<String> ids) {
        List<ObjectId> objectIds = ids.stream().map(ObjectId::new).collect(Collectors.toList());
        return personCollection.find(in("_id", objectIds)).into(new ArrayList<>());
    }

    @Override
    public Person findOne(String id) {
        return personCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public long count() {
        return personCollection.countDocuments();
    }

    @Override
    public long delete(String id) {
        return personCollection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }

    @Override
    public long delete(List<String> ids) {
        List<ObjectId> objectIds = ids.stream().map(ObjectId::new).collect(Collectors.toList());
        return personCollection.deleteMany(in("_id", objectIds)).getDeletedCount();
    }

    @Override
    public long deleteAll() {
        return personCollection.deleteMany(new BsonDocument()).getDeletedCount();
    }

    @Override
    public Person update(Person person) {
        return personCollection.findOneAndReplace(eq("_id", person.getId()), person);
    }

    @Override
    public long update(List<Person> persons) {
        List<WriteModel<Person>> writes = new ArrayList<>();
        persons.forEach(p -> writes.add(new ReplaceOneModel<>(eq("_id", p.getId()), p)));
        return personCollection.bulkWrite(writes).getModifiedCount();
    }

}
