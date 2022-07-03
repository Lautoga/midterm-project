package com.ironhack.midtermproject.controller.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.classes.Address;
import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.model.CreditCard;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.CreditCardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CreditCardControllerImplTest {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private CreditCard creditCard1, creditCard2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
       AccountHolder accountHolder1 = new AccountHolder("Maria","1234",
                LocalDate.of(1988,12,9),new Address("asd","Alicante",03003));
        AccountHolder accountHolder2 = new AccountHolder("Ana","5678",
                LocalDate.of(2000,2,23),new Address("ghj","Valencia",46003));
        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        creditCard1 =new CreditCard(1L,new Money(new BigDecimal(2000)), accountHolder1,
                new Money(new BigDecimal(1000)),new BigDecimal("0.15"));
        creditCard2 =new CreditCard(2L,new Money(new BigDecimal(2500)), accountHolder2,
                new Money(new BigDecimal(900)),new BigDecimal("0.18"));
        creditCardRepository.saveAll(List.of(creditCard1,creditCard2));
    }

    @AfterEach
    void tearDown() {
        creditCardRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/credit-card"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ana"));    }


    @Test
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/credit-card/"+creditCard1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ana"));
    }

    @Test
    void store() throws Exception {
        AccountHolder accountHolder1 = new AccountHolder("Maria","1234",
                LocalDate.of(1988,12,9),new Address("asd","Alicante",03003));

        CreditCard creditCard = new CreditCard(5L,new Money(new BigDecimal(3000)),accountHolder1,
                new Money(new BigDecimal(1000)),new BigDecimal("0.13"));
        String body = objectMapper.writeValueAsString(creditCard);


        MvcResult mvcResult = mockMvc.perform(
                        post("/credit-card")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("0.13"));


        assertTrue(creditCardRepository.existsById(creditCard.getId()));
    }




    @Test
    void delete() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/credit-card/" + creditCard1.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(creditCardRepository.existsById(creditCard1.getId()));
    }
}
