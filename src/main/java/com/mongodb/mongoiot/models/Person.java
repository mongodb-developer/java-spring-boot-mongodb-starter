package com.mongodb.mongoiot.models;

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

    private class Car {
        private String brand;
        private String model;
        private Float maxSpeedKmH;

        public String getBrand() {
            return brand;
        }

        public Car setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public String getModel() {
            return model;
        }

        public Car setModel(String model) {
            this.model = model;
            return this;
        }

        public Float getMaxSpeedKmH() {
            return maxSpeedKmH;
        }

        public Car setMaxSpeedKmH(Float maxSpeedKmH) {
            this.maxSpeedKmH = maxSpeedKmH;
            return this;
        }

        @Override
        public String toString() {
            return "Car{" + "brand='" + brand + '\'' + ", model='" + model + '\'' + ", maxSpeedKmH=" + maxSpeedKmH + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Car car = (Car) o;
            return Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(maxSpeedKmH,
                                                                                                          car.maxSpeedKmH);
        }

        @Override
        public int hashCode() {
            return Objects.hash(brand, model, maxSpeedKmH);
        }
    }

    private class Address {
        private int number;
        private String street;
        private String postcode;
        private String city;
        private String country;

        public int getNumber() {
            return number;
        }

        public Address setNumber(int number) {
            this.number = number;
            return this;
        }

        public String getStreet() {
            return street;
        }

        public Address setStreet(String street) {
            this.street = street;
            return this;
        }

        public String getPostcode() {
            return postcode;
        }

        public Address setPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public String getCity() {
            return city;
        }

        public Address setCity(String city) {
            this.city = city;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public Address setCountry(String country) {
            this.country = country;
            return this;
        }

        @Override
        public String toString() {
            return "Address{" + "number=" + number + ", street='" + street + '\'' + ", postcode='" + postcode + '\'' + ", city='" + city + '\'' + ", country='" + country + '\'' + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Address address = (Address) o;
            return number == address.number && Objects.equals(street, address.street) && Objects.equals(postcode,
                                                                                                        address.postcode) && Objects
                    .equals(city, address.city) && Objects.equals(country, address.country);
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, street, postcode, city, country);
        }
    }
}