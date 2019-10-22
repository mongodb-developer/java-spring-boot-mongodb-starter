# Quick Start Java & MongoDB Project

## Supported versions:

- Java 8 to 13
- Spring boot 2.2.0.RELEASE
- MongoDB 4.2.1
- MongoDB Java driver 3.11.1
- Maven 3.6.2

## MongoDB Atlas

 - Get started with a Free Tier Cluster on [MongoDB Atlas](http://bit.ly/mongodb-meetatlas).
 - Read this blog post: [Quick Start - Getting your Free MongoDB Atlas Cluster](https://www.mongodb.com/blog/post/quick-start-getting-your-free-mongodb-atlas-cluster).
- You will need to update the default MongoDB URI `spring.data.mongodb.uri` in the `application.properties` file.

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
