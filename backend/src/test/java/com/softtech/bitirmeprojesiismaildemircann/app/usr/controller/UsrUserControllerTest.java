package com.softtech.bitirmeprojesiismaildemircann.app.usr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.bitirmeprojesiismaildemircann.BitirmeprojesiIsmaildemircannApplication;
import com.softtech.bitirmeprojesiismaildemircann.app.BaseTest;
import com.softtech.bitirmeprojesiismaildemircann.app.config.H2TestProfileJPAConfig;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {BitirmeprojesiIsmaildemircannApplication.class, H2TestProfileJPAConfig.class})
class UsrUserControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/users";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void saveUser() throws Exception {

        UsrUserSaveRequestDto usrUserSaveRequestDto = UsrUserSaveRequestDto.builder()
                .username("TEST:ismaildemircann")
                .name("ismail")
                .surname("demircan")
                .password("123456")
                .build();

        String content = objectMapper.writeValueAsString(usrUserSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateUser() throws Exception {

        UsrUserUpdateRequestDto usrUserUpdateRequestDto = UsrUserUpdateRequestDto.builder()
                .id(2L)
                .username("TEST2:ismaildemircann")
                .name("test")
                .surname("user")
                .password("12345678")
                .build();

        String content = objectMapper.writeValueAsString(usrUserUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void deleteUser() throws Exception {
        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/2").content("2L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}