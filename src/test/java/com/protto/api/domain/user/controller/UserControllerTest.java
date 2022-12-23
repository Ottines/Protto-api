package com.protto.api.domain.user.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.protto.api.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.protto.api.domain.user.model.dao.UserCreateRequest;
import com.protto.api.domain.user.model.dao.UserVO;
import com.protto.api.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private final UserVO user1 = new UserVO(1L, "Ottman", "Becuwe", "ottinestv@gmail.com");
    private final UserVO user2 = new UserVO(2L, "William", "Becuwe", "willinestv@gmail.com");


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void whenCreateUser_then_expectStatusCreated() throws Exception {
        UserCreateRequest userCreateRequest = new UserCreateRequest(user1.firstName(), user1.lastName(), user1.email(), "Password77");
        Mockito.when(userService.create(userCreateRequest)).thenReturn(1L);

        this.mockMvc.perform(post("/person/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.toJSON(userCreateRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(TestUtil.toJSON(user1.id())))
                .andReturn();
    }

    @Test
    void whenGetAllUsers_then_expectStatusOkAndListIsNotEmpty() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        MvcResult result = mockMvc.perform(get("/person")
                                .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();
        List<UserVO> userVOList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
        assertThat(userVOList).contains(user1).contains(user2);
    }

    @Test
    void whenFindByID_then_expectStatusOk() throws Exception {
        Mockito.when(userService.findById(1L)).thenReturn(user1);

        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtil.toJSON(user1)))
                .andReturn();
    }

}
