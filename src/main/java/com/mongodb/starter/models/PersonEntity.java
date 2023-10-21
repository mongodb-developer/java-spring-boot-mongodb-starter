package com.mongodb.starter.models;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PersonEntity {

    private ObjectId id;
    private String firstName;
    private String lastName;
    private int age;
    private AddressEntity addressEntity;
    private Date createdAt = new Date();
    private Boolean insurance;
    private List<CarEntity> carEntities;

    public PersonEntity() {
    }

    public PersonEntity(ObjectId id,
                        String firstName,
                        String lastName,
                        int age,
                        AddressEntity addressEntity,
                        Date createdAt,
                        Boolean insurance,
                        List<CarEntity> carEntities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.addressEntity = addressEntity;
        this.createdAt = createdAt;
        this.insurance = insurance;
        this.carEntities = carEntities;
    }

    public ObjectId getId() {
        return id;
    }

    public PersonEntity setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public PersonEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public AddressEntity getAddress() {
        return addressEntity;
    }

    public PersonEntity setAddress(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public PersonEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public PersonEntity setInsurance(Boolean insurance) {
        this.insurance = insurance;
        return this;
    }

    public List<CarEntity> getCars() {
        return carEntities;
    }

    public PersonEntity setCars(List<CarEntity> carEntities) {
        this.carEntities = carEntities;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + ", address=" + addressEntity + ", createdAt=" + createdAt + ", insurance=" + insurance + ", cars=" + carEntities + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity personEntity = (PersonEntity) o;
        return age == personEntity.age && Objects.equals(id, personEntity.id) && Objects.equals(firstName,
                                                                                                personEntity.firstName) && Objects.equals(
                lastName, personEntity.lastName) && Objects.equals(addressEntity,
                                                                   personEntity.addressEntity) && Objects.equals(
                createdAt, personEntity.createdAt) && Objects.equals(insurance,
                                                                     personEntity.insurance) && Objects.equals(
                carEntities, personEntity.carEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, addressEntity, createdAt, insurance, carEntities);
    }

}
