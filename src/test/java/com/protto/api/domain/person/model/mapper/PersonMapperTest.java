package com.protto.api.domain.person.model.mapper;

import com.protto.api.data.entities.PersonEntity;
import com.protto.api.domain.person.model.dao.PersonVO;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class PersonMapperTest {

    private final PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);
    private final PersonEntity personEntity = PersonEntity.builder()
            .id(1L)
            .firstName("Ottman")
            .lastName("BECUWE")
            .email("ottinestv@gmail.com")
            .password("Totototo77")
            .build();

    @Test
    void givenPersonEntity_whenMapToPersonVO_thenShouldExpectSameVO() {
        PersonVO p = personMapper.entityToPersonVO(personEntity);

        assertThat(p.id()).isEqualTo(personEntity.getId());
        assertThat(p.firstName()).isEqualTo(personEntity.getFirstName());
        assertThat(p.lastName()).isEqualTo(personEntity.getLastName());
        assertThat(p.email()).isEqualTo(personEntity.getEmail());
    }

    @Test
    void givenPersonEntityNull_whenMapToPersonVO_thenShouldExpectNull() {
        PersonVO p = personMapper.entityToPersonVO(null);

        assertNull(p);
    }
}
