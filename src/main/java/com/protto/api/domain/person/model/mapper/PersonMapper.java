package com.protto.api.domain.person.model.mapper;

import com.protto.api.data.entities.PersonEntity;
import com.protto.api.domain.person.model.dao.PersonVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonVO entityToPersonVO(PersonEntity personEntity);

}
