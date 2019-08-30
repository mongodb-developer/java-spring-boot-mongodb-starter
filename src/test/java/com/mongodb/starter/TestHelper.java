package com.mongodb.starter;

import com.mongodb.starter.models.Person;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TestHelper {

    Person getMax() {
        Person.Address address = new Person.Address().setCity("Paris")
                                                     .setCountry("France")
                                                     .setNumber(1)
                                                     .setPostcode("12345")
                                                     .setStreet("The Best Street");
        List<Person.Car> cars = Collections.singletonList(
                new Person.Car().setBrand("Ferrari").setMaxSpeedKmH(339f).setModel("SF90 Stradale"));
        return new Person().setFirstName("Maxime")
                           .setLastName("Beugnet")
                           .setAddress(address)
                           .setAge(31)
                           .setInsurance(true)
                           .setCars(cars);
    }

    Person getAlex() {
        Person.Address address = new Person.Address().setCity("Toulouse")
                                                     .setCountry("France")
                                                     .setNumber(2)
                                                     .setPostcode("54321")
                                                     .setStreet("Another Street");
        List<Person.Car> cars = Collections.singletonList(
                new Person.Car().setBrand("Mercedes").setMaxSpeedKmH(355f).setModel("Project One"));
        return new Person().setFirstName("Alex")
                           .setLastName("Beugnet")
                           .setAddress(address)
                           .setAge(27)
                           .setInsurance(false)
                           .setCars(cars);

    }

}
