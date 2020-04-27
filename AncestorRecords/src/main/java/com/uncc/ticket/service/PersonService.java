package com.uncc.ticket.service;

import com.uncc.ticket.model.PersonEntity;
import com.uncc.ticket.model.UsersEntity;
import com.uncc.ticket.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {
    private PersonRepository PersonRepository;

    @Autowired
    public PersonService(PersonRepository PersonRepository){
        this.PersonRepository=PersonRepository;
    }

    @Transactional
    public PersonEntity storePerson(PersonEntity Person){
        return PersonRepository.save(Person);
    }

    public List<PersonEntity> getAllPersons(){
        return PersonRepository.findAll();
    }

    public PersonEntity findById(Long id) { return PersonRepository.getOne(id); }

    @Transactional
    public void deleteById(Long id) { PersonRepository.deleteById(id); }
}
