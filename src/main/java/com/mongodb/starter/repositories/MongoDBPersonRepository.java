package com.mongodb.starter.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.*;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.WriteModel;
import com.mongodb.starter.dtos.AverageAgeDTO;
import com.mongodb.starter.models.Person;
import org.bson.BsonDocument;
import org.bson.BsonNull;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Projections.excludeId;
import static java.util.Arrays.asList;

@Repository
public class MongoDBPersonRepository implements PersonRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
                                                                           .readPreference(ReadPreference.primaryPreferred())
                                                                           .readConcern(ReadConcern.MAJORITY)
                                                                           .writeConcern(WriteConcern.MAJORITY)
                                                                           .build();
    private final MongoCollection<Person> personCollection;
    private final MongoClient client;

    @Autowired
    public MongoDBPersonRepository(MongoClient mongoClient) {
        client = mongoClient;
        MongoDatabase db = mongoClient.getDatabase("test");
        personCollection = db.getCollection("persons", Person.class);
    }

    @Override
    public Person save(Person person) {
        person.setId(new ObjectId());
        personCollection.insertOne(person);
        return person;
    }

    @Override
    public List<Person> saveAll(List<Person> persons) {
        List<Person> people;
        try (ClientSession clientSession = client.startSession()) {
            persons.forEach(p -> p.setId(new ObjectId()));
            TransactionBody<List<Person>> txnBody = () -> {
                personCollection.insertMany(clientSession, persons);
                return persons;
            };
            people = clientSession.withTransaction(txnBody, txnOptions);
        }
        return people;
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
        Long count;
        List<ObjectId> objectIds = ids.stream().map(ObjectId::new).collect(Collectors.toList());
        try (ClientSession clientSession = client.startSession()) {
            TransactionBody<Long> txnBody = () -> personCollection.deleteMany(clientSession, in("_id", objectIds))
                                                                  .getDeletedCount();
            count = clientSession.withTransaction(txnBody, txnOptions);
        }
        return count;
    }

    @Override
    public long deleteAll() {
        return personCollection.deleteMany(new BsonDocument()).getDeletedCount();
    }

    @Override
    public Person update(Person person) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions();
        options.returnDocument(ReturnDocument.AFTER);
        return personCollection.findOneAndReplace(eq("_id", person.getId()), person, options);
    }

    @Override
    public long update(List<Person> persons) {
        int modifiedCount;
        List<WriteModel<Person>> writes = persons.stream()
                                                 .map(p -> new ReplaceOneModel<>(eq("_id", p.getId()), p))
                                                 .collect(Collectors.toList());
        try (ClientSession clientSession = client.startSession()) {
            modifiedCount = clientSession.withTransaction(
                    () -> personCollection.bulkWrite(clientSession, writes).getModifiedCount(), txnOptions);
        }
        return modifiedCount;
    }

    @Override
    public double getAverageAge() {
        List<Bson> pipeline = asList(group(new BsonNull(), avg("averageAge", "$age")), project(excludeId()));
        return personCollection.aggregate(pipeline, AverageAgeDTO.class).first().getAverageAge();
    }

}
