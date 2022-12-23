package com.protto.api.domain.user.service;


import com.protto.api.data.repositories.UserRepository;
import com.protto.api.domain.user.model.dao.UserCreateRequest;
import com.protto.api.domain.user.model.dao.UserVO;
import com.protto.api.domain.user.model.mapper.UserMapper;
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
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService(userRepository, userMapper);
    }

    @Test
    @Sql("/User.sql")
    void whenCreateUser_thenShouldSuccess() {
        UserCreateRequest userCreateRequest = new UserCreateRequest("Robert", "Becuwe", "robert@gmail.com", "ouiouioui77");

        assertThat(userService.create(userCreateRequest)).isEqualTo(4L);
    }

    @Test
    @Sql("/User.sql")
    void whenGetAllUser_thenShouldReturnGoodSizeList() {
        List<UserVO> userVOList = userService.getAllUsers();

        assertNotNull(userVOList);
        assertThat(userVOList).hasSize(3);
    }

    @Test
    @Sql("/User.sql")
    void whenFindUserById_thenShouldExpectGoodUser() {
        UserVO userVO = userService.findById(2L);

        assertThat(userVO.firstName()).isEqualTo("William");
        assertThat(userVO.lastName()).isEqualTo("Becuwe");
    }
}
