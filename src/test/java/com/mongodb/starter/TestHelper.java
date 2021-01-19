package com.mongodb.starter;

import com.mongodb.starter.models.Address;
import com.mongodb.starter.models.Car;
import com.mongodb.starter.models.Person;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Component
class TestHelper {

    Person getMax() {
        return new Person().setFirstName("Maxime")
                           .setLastName("Beugnet")
                           .setAddress(new Address().setCity("Paris")
                                                    .setCountry("France")
                                                    .setNumber(1)
                                                    .setPostcode("12345")
                                                    .setStreet("The Best Street"))
                           .setAge(31)
                           .setInsurance(true)
                           .setCars(singletonList(new Car().setBrand("Ferrari").setMaxSpeedKmH(339f).setModel("SF90 Stradale")));
    }

    Person getAlex() {
        return new Person().setFirstName("Alex")
                           .setLastName("Beugnet")
                           .setAddress(new Address().setCity("Toulouse")
                                                    .setCountry("France")
                                                    .setNumber(2)
                                                    .setPostcode("54321")
                                                    .setStreet("Another Street"))
                           .setAge(27)
                           .setInsurance(false)
                           .setCars(singletonList(new Car().setBrand("Mercedes").setMaxSpeedKmH(355f).setModel("Project One")));
    }

    List<Person> getListMaxAlex() {
        return asList(getMax(), getAlex());
    }
}
