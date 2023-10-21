package com.mongodb.starter;

import com.mongodb.starter.dtos.PersonDTO;
import com.mongodb.starter.models.AddressEntity;
import com.mongodb.starter.models.CarEntity;
import com.mongodb.starter.models.PersonEntity;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TestHelper {

    PersonEntity getMaxEntity() {
        return new PersonEntity().setFirstName("Maxime")
                                 .setLastName("Beugnet")
                                 .setAddress(new AddressEntity().setCity("Paris")
                                                                .setCountry("France")
                                                                .setNumber(1)
                                                                .setPostcode("12345")
                                                                .setStreet("The Best Street"))
                                 .setAge(31)
                                 .setInsurance(true)
                                 .setCars(List.of(new CarEntity().setBrand("Ferrari")
                                                                 .setMaxSpeedKmH(339f)
                                                                 .setModel("SF90 Stradale")));
    }

    PersonEntity getAlexEntity() {
        return new PersonEntity().setFirstName("Alex")
                                 .setLastName("Beugnet")
                                 .setAddress(new AddressEntity().setCity("Toulouse")
                                                                .setCountry("France")
                                                                .setNumber(2)
                                                                .setPostcode("54321")
                                                                .setStreet("Another Street"))
                                 .setAge(27)
                                 .setInsurance(false)
                                 .setCars(List.of(new CarEntity().setBrand("Mercedes")
                                                                 .setMaxSpeedKmH(355f)
                                                                 .setModel("Project One")));
    }

    PersonDTO getMaxDTO() {
        return new PersonDTO(getMaxEntity());
    }

    public PersonDTO getMaxDTOWithId(ObjectId id) {
        return new PersonDTO(getMaxEntity().setId(id));
    }

    PersonDTO getAlexDTO() {
        return new PersonDTO(getAlexEntity());
    }

    PersonDTO getAlexDTOWithId(ObjectId id) {
        return new PersonDTO(getAlexEntity().setId(id));
    }

    List<PersonEntity> getListMaxAlexEntity() {
        return List.of(getMaxEntity(), getAlexEntity());
    }

    List<PersonDTO> getListMaxAlexDTO() {
        return List.of(getMaxDTO(), getAlexDTO());
    }
}
