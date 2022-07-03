package com.ironhack.midtermproject.controller.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.Utils.Address;
import com.ironhack.midtermproject.Utils.Money;
import com.ironhack.midtermproject.enums.Status;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.model.StudentChecking;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.StudentCheckingRepository;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class StudentCheckingControllerImplTest {
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private AccountHolder accountHolder1, accountHolder2;

    private StudentChecking studentChecking1, studentChecking2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolder1 = new AccountHolder("Maria","1234",
                LocalDate.of(1988,12,9),new Address("asd","Alicante",03003));
        accountHolder2 = new AccountHolder("Ana","5678",
                LocalDate.of(2000,2,23),new Address("ghj","Valencia",46003));
        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        studentChecking1 = new StudentChecking(34L,new Money(new BigDecimal(2000)),accountHolder1,"12345",
                new Date(2005-3-23),Status.ACTIVE);

        studentChecking2 = new StudentChecking(30L,new Money(new BigDecimal(2500)),accountHolder2,
                "124556",new Date(2000-12-12), Status.ACTIVE);
        studentCheckingRepository.saveAll(List.of(studentChecking1,studentChecking2));
    }

    @AfterEach
    void tearDown() {
        studentCheckingRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/student-checking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ana"));
    }

    @Test
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/student-checking/"+studentChecking1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ana"));
    }

}