package com.protto.api.domain.user.model.mapper;

import com.protto.api.data.entities.UserEntity;

import com.protto.api.domain.user.model.dao.UserVO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserMapperTest {

    private final UserMapper userMapper= Mappers.getMapper(UserMapper.class);
    private final UserEntity userEntity = UserEntity.builder()
            .id(1L)
            .firstName("Ottman")
            .lastName("BECUWE")
            .email("ottinestv@gmail.com")
            .password("Totototo77")
            .build();

    @Test
    void givenUserEntity_whenMapToUserVO_thenShouldExpectSameVO() {
        UserVO p = userMapper.entityToUserVO(userEntity);

        assertThat(p.id()).isEqualTo(userEntity.getId());
        assertThat(p.firstName()).isEqualTo(userEntity.getFirstName());
        assertThat(p.lastName()).isEqualTo(userEntity.getLastName());
        assertThat(p.email()).isEqualTo(userEntity.getEmail());
    }

    @Test
    void givenUserEntityNull_whenMapToUserVO_thenShouldExpectNull() {
        UserVO user = userMapper.entityToUserVO(null);

        assertNull(user);
    }
}
