package com.mongodb.mongoiot;

// import com.mongodb.MongoClient;
// import com.mongodb.MongoClientOptions;
// import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class ConfigurationSpring {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Bean
    public MongoClient mongoClient() {
        /*CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                                                     fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientOptions.Builder options = new MongoClientOptions.Builder().codecRegistry(codecRegistry);
        MongoClientURI uri = new MongoClientURI(connectionString, options);
        return new MongoClient(uri);*/

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                                                         fromProviders(PojoCodecProvider.builder().automatic(true).build()));


        return MongoClients.create("mongodb://hostOne:27017,hostTwo:27018");


    }

}
