package com.mongodb.starter.services;

import com.mongodb.starter.dtos.PersonDTO;
import com.mongodb.starter.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDTO save(PersonDTO PersonDTO) {
        return new PersonDTO(personRepository.save(PersonDTO.toPersonEntity()));
    }

    @Override
    public List<PersonDTO> saveAll(List<PersonDTO> personEntities) {
        return personEntities.stream()
                             .map(PersonDTO::toPersonEntity)
                             .peek(personRepository::save)
                             .map(PersonDTO::new)
                             .toList();
    }

    @Override
    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream().map(PersonDTO::new).toList();
    }

    @Override
    public List<PersonDTO> findAll(List<String> ids) {
        return personRepository.findAll(ids).stream().map(PersonDTO::new).toList();
    }

    @Override
    public PersonDTO findOne(String id) {
        return new PersonDTO(personRepository.findOne(id));
    }

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public long delete(String id) {
        return personRepository.delete(id);
    }

    @Override
    public long delete(List<String> ids) {
        return personRepository.delete(ids);
    }

    @Override
    public long deleteAll() {
        return personRepository.deleteAll();
    }

    @Override
    public PersonDTO update(PersonDTO PersonDTO) {
        return new PersonDTO(personRepository.update(PersonDTO.toPersonEntity()));
    }

    @Override
    public long update(List<PersonDTO> personEntities) {
        return personRepository.update(personEntities.stream().map(PersonDTO::toPersonEntity).toList());
    }

    @Override
    public double getAverageAge() {
        return personRepository.getAverageAge();
    }
}
