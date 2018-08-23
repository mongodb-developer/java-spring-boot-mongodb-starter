package com.mongodb.starter;
// import com.mongodb.MongoClient;
// import com.mongodb.MongoClientOptions;
// import com.mongodb.MongoClientURI;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class ConfigurationSpring {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Bean
    public MongoClient mongoClient() {
        CodecRegistry codecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), codecRegistry);
        Block<ClusterSettings.Builder> connection = b -> b.applyConnectionString(new ConnectionString(connectionString)).build();
        MongoClientSettings settings = MongoClientSettings.builder()
                                                          .applyToClusterSettings(connection)
                                                          .codecRegistry(pojoCodecRegistry)
                                                          .build();
        return MongoClients.create(settings);
    }

}
