package com.softtech.bitirmeprojesiismaildemircann.app.prd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.bitirmeprojesiismaildemircann.BitirmeprojesiIsmaildemircannApplication;
import com.softtech.bitirmeprojesiismaildemircann.app.BaseTest;
import com.softtech.bitirmeprojesiismaildemircann.app.config.H2TestProfileJPAConfig;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductUpdateRequestDto;
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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {BitirmeprojesiIsmaildemircannApplication.class, H2TestProfileJPAConfig.class})
class PrdProductControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/products";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void saveProduct() throws Exception {

        PrdProductSaveRequestDto prdProductSaveRequestDto = PrdProductSaveRequestDto.builder()
                .name("Orange")
                .taxFreePrice(BigDecimal.TEN)
                .productCategoryId(1L)
                .build();

        String content = objectMapper.writeValueAsString(prdProductSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findAllProducts() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findAllProductsByCategory() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findAllProductsByPriceFilter() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "?minPrice=1&maxPrice=999").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void deleteProduct() throws Exception {
        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/2").content("2L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateProduct() throws Exception {

        PrdProductUpdateRequestDto prdProductUpdateRequestDto = PrdProductUpdateRequestDto.builder()
                .id(2L)
                .name("Banana")
                .taxFreePrice(BigDecimal.ONE)
                .productCategoryId(1L)
                .build();

        String content = objectMapper.writeValueAsString(prdProductUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateProductPrice() throws Exception {

        MvcResult result = mockMvc.perform(
                patch(BASE_PATH + "/3?newProductTaxFreePrice=20").content("3L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}