package com.protto.api.domain.person.service;


import com.protto.api.data.repositories.PersonRepository;
import com.protto.api.domain.person.model.dao.PersonCreateRequest;
import com.protto.api.domain.person.model.dao.PersonVO;
import com.protto.api.domain.person.model.mapper.PersonMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class personServiceTest {

    @Autowired
    private PersonRepository personRepository;
    private PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);
    private PersonService personService;

    @BeforeEach
    void init() {
        personService = new PersonService(personRepository, personMapper);
    }

    @Test
    @Sql("/Person.sql")
    void whenCreatePerson_thenShouldSuccess() {
        PersonCreateRequest personCreateRequest = new PersonCreateRequest("Robert", "Becuwe", "robert@gmail.com", "ouiouioui77");

        assertThat(personService.create(personCreateRequest)).isEqualTo(4L);
    }

    @Test
    @Sql("/Person.sql")
    void whenGetAllPerson_thenShouldReturnGoodSizeList() {
        List<PersonVO> personVOList = personService.getAllPersons();

        assertNotNull(personVOList);
        assertThat(personVOList).hasSize(3);
    }

    @Test
    @Sql("/Person.sql")
    void whenFindPersonById_thenShouldExpectGoodPerson() {
        PersonVO personVO = personService.findById(2L);

        assertThat(personVO.firstName()).isEqualTo("William");
        assertThat(personVO.lastName()).isEqualTo("Becuwe");
    }
}
