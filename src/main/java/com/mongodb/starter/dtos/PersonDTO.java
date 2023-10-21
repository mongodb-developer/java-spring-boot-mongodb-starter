package com.mongodb.starter.dtos;

import com.mongodb.starter.models.PersonEntity;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public record PersonDTO(
        String id,
        String firstName,
        String lastName,
        int age,
        AddressDTO address,
        Date createdAt,
        Boolean insurance,
        List<CarDTO> cars) {

    public PersonDTO(PersonEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getFirstName(),
             p.getLastName(), p.getAge(), new AddressDTO(p.getAddress()), p.getCreatedAt(), p.getInsurance(),
             p.getCars().stream().map(CarDTO::new).toList());
    }

    public PersonEntity toPersonEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new PersonEntity(_id, firstName, lastName, age, address.toAddressEntity(), createdAt, insurance,
                                cars.stream().map(CarDTO::toCarEntity).toList());
    }
}
