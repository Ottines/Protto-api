package com.protto.api.domain.person.service;

import com.protto.api.data.entities.PersonEntity;
import com.protto.api.data.repositories.PersonRepository;
import com.protto.api.domain.person.model.mapper.PersonMapper;
import com.protto.api.domain.person.model.dao.PersonCreateRequest;
import com.protto.api.domain.person.model.dao.PersonVO;

import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    //@Autowired
    //private PasswordEncoder passwordEncoder;
    @Transactional(propagation = Propagation.REQUIRED)
    public Long create(PersonCreateRequest personCreateRequest) {
        if(isAlreadyRegistered(personCreateRequest.email())) {
            throw new RuntimeException(); //exception perso a throw et pas du generic
        }
        PersonEntity person = new PersonEntity();
        person.setFirstName(personCreateRequest.firstName());
        person.setLastName(personCreateRequest.lastName());
        person.setEmail(personCreateRequest.email());
        person.setPassword(personCreateRequest.password());
        //person.setPassword(passwordEncoder.encode(personCreateRequest.password()));
        return personRepository.save(person).getId();
    }
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<PersonVO> getAllPersons() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .map(personMapper::entityToPersonVO)
                .toList();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public PersonVO findById(Long id) {
        return personRepository.findById(id)
                .map(personMapper::entityToPersonVO)
                .orElseThrow(RuntimeException::new); //Modifier ca par une exception perso
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public boolean isAlreadyRegistered(String email) {
        return personRepository.findByEmail(email).isPresent();
    }


}
