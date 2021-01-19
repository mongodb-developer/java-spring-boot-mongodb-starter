# Quick Start Java & MongoDB Project

## Supported versions:

- Java 8 to 15
- Spring boot 2.4.2
- MongoDB 4.4.3
- MongoDB Java driver 4.1.1
- Maven 3.6.3
- Swagger 3.0.0

## MongoDB Atlas

- Get started with a Free Tier Cluster on [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).
- Read this blog post: [Quick Start - Getting your Free MongoDB Atlas Cluster](https://developer.mongodb.com/quickstart/free-atlas-cluster).
- You will need to update the default MongoDB URI `spring.data.mongodb.uri` in the `application.properties` file.

## Commands

- Start the server in a console with `mvn spring-boot:run`.
- If you add some Unit Tests, you would start them with `mvn clean test`.
- You can start the end to end tests with `mvn clean integration-test`.
- You can build the project with : `mvn clean package`.
- You can run the project with the fat jar and the embedded Tomcat: `java -jar target/java-spring-boot-mongodb-starter-1.0.0.jar` but I would use a real tomcat in production.

## Swagger 3
- Swagger 3 is already configured in this project in `SpringFoxConfig.java`.
- The Swagger UI can be seen at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
- The Swagger API documentation 2.0 is at [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs).
- The Open API documentation 3.0.3 is at [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs).
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
- You don't have to use Spring Data MongoDB. The MongoDB driver is more flexible and already provides everything you need to code efficiently and optimise your queries correctly.

## Author
- Maxime Beugnet @ MongoDB.
