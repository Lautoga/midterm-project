package com.ironhack.midtermproject.controller.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.model.Savings;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.SavingsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SavingsControllerImplTest {
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Savings savings1, savings2;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() throws Exception {

    }

    @Test
    void store() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }
}