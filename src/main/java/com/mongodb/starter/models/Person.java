package com.mongodb.starter.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class Person {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String firstName;
    private String lastName;
    private int age;
    private Address address;
    private Date createdAt = new Date();
    private Boolean insurance;
    private List<Car> cars;

    public ObjectId getId() {
        return id;
    }

    public Person setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Person setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Person setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public Person setInsurance(Boolean insurance) {
        this.insurance = insurance;
        return this;
    }

    public List<Car> getCars() {
        return cars;
    }

    public Person setCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + ", address=" + address + ", createdAt=" + createdAt + ", insurance=" + insurance + ", cars=" + cars + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(id, person.id) && Objects.equals(firstName,
                                                                                    person.firstName) && Objects.equals(lastName,
                                                                                                                        person.lastName) && Objects
                .equals(address, person.address) && Objects.equals(createdAt, person.createdAt) && Objects.equals(insurance,
                                                                                                                  person.insurance) && Objects
                .equals(cars, person.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, address, createdAt, insurance, cars);
    }

}