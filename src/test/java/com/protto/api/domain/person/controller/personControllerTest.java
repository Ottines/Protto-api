package com.protto.api.domain.person.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.protto.api.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.protto.api.domain.person.model.dao.PersonCreateRequest;
import com.protto.api.domain.person.model.dao.PersonVO;
import com.protto.api.domain.person.service.PersonService;
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

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
class personControllerTest {

    @MockBean
    private PersonService personService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private final PersonVO person1 = new PersonVO(1L, "Ottman", "Becuwe", "ottinestv@gmail.com");
    private final PersonVO person2 = new PersonVO(2L, "William", "Becuwe", "willinestv@gmail.com");


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void whenCreatePerson_then_expectStatusCreated() throws Exception {
        PersonCreateRequest personCreateRequest = new PersonCreateRequest(person1.firstName(), person1.lastName(), person1.email(), "Password77");
        Mockito.when(personService.create(personCreateRequest)).thenReturn(1L);

        this.mockMvc.perform(post("/person/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.toJSON(personCreateRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(TestUtil.toJSON(person1.id())))
                .andReturn();
    }

    @Test
    void whenGetAllPerson_then_expectStatusOkAndListIsNotEmpty() throws Exception {
        Mockito.when(personService.getAllPersons()).thenReturn(Arrays.asList(person1, person2));

        MvcResult result = mockMvc.perform(get("/person")
                                .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();

        List<PersonVO> personVOList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
        assertThat(personVOList).contains(person1).contains(person2);
    }

    @Test
    void whenFindByID_then_expectStatusOk() throws Exception {
        Mockito.when(personService.findById(1L)).thenReturn(person1);

        mockMvc.perform(get("/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtil.toJSON(person1)))

                .andReturn();
    }

}
