# Quick Start Java & MongoDB Project

## Supported versions:

- Java 8 to 12
- Spring boot 2.1.8.RELEASE
- MongoDB 4.2.0
- MongoDB Java driver 3.11.0
- Maven 3.6.0

## MongoDB

Of course, you will need MongoDB to use this project.

You have 3 choices:

#### Use MongoDB Atlas

- Ideally, you will have to spin up a MongoDB 4.2 cluster to avoid surprises.
- You will need to update the default `spring.data.mongodb.uri` in `application.properties`.

#### Use a single node Replica Set in Docker

- You need at least a single node Replica Set for the ACID transactions.
- `startMongoDBDocker.sh` to start...
- `stopMongoDBDocker.sh` to stop.

#### Use something else
- DIY :-).
- If you are thinking about using some "MongoDB fake copycat". This is going to fail miserably. They don't support transactions to say the least.

## Commands

- Start the server in a console with `mvn spring-boot:run`.
- If you add some Unit Tests, you would start them with `mvn clean test`.
- You can start the end to end tests with `mvn clean integration-test`.
- You can build the project with : `mvn clean package`.
- You can run the project with the fat jar and the embedded Tomcat: `java -jar target/java-spring-boot-mongodb-starter-1.0.0.jar` but I would use a real tomcat in production.

## Swagger
- Swagger is already configured in this project in `SwaggerConfig.java`.
- The API can be seen at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
- You can also try the entire REST API directly from the Swagger interface!

## Features showcase
This project showcases several features of MongoDB:

- MongoDB multi-document ACID transactions for 3 functions. See `MongoDBPersonRepository.saveAll()`.
- MongoDB Aggregation pipeline. See `MongoDBPersonRepository.getAverageAge()`.
- Implementation of basic CRUD queries. See `MongoDBPersonRepository.java`.
- MongoDB typed collection with automatic mapping to POJOs using codecs: See `ConfigurationSpring.java`.
- How to manipulate correctly ObjectidId across, the REST API, the POJOs and the database itself. See the main trick in `Person.java`.

And some other cool stuff:
- You can change the default Spring Boot logo by adding a banner.txt file in your properties.
- You don't have to use Spring Data MongoDB. The MongoDB driver is more flexible and already provide everything you need to code efficiently and optimise your queries correctly.

## Author
- Maxime Beugnet @ MongoDB.
