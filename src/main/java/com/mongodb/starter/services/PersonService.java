package com.mongodb.starter.services;


import com.mongodb.starter.dtos.PersonDTO;

import java.util.List;

public interface PersonService {

    PersonDTO save(PersonDTO PersonDTO);

    List<PersonDTO> saveAll(List<PersonDTO> personEntities);

    List<PersonDTO> findAll();

    List<PersonDTO> findAll(List<String> ids);

    PersonDTO findOne(String id);

    long count();

    long delete(String id);

    long delete(List<String> ids);

    long deleteAll();

    PersonDTO update(PersonDTO PersonDTO);

    long update(List<PersonDTO> personEntities);

    double getAverageAge();
    
}
