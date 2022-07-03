package com.ironhack.midtermproject.controller.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.Utils.Address;
import com.ironhack.midtermproject.Utils.Money;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.model.CreditCard;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.AccountRepository;
import com.ironhack.midtermproject.repository.CreditCardRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerImplTest {



    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();


    private AccountHolder accountHolder1, accountHolder2;
    private CreditCard creditCard1, creditCard2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolder1 = new AccountHolder("Maria","1234",
                LocalDate.of(1988,12,9),new Address("asd","Alicante",03003));
        accountHolder2 = new AccountHolder("Ana","5678",
                LocalDate.of(2000,2,23),new Address("ghj","Valencia",46003));
        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));
        creditCard1 =new CreditCard(1L,new Money(new BigDecimal(2000)),accountHolder1,
                new Money(new BigDecimal(1000)), new Date(2014-5-3),LocalDate.now(),new BigDecimal(0.15));
        creditCard2 =new CreditCard(2L,new Money(new BigDecimal(2500)), accountHolder2,
                new Money(new BigDecimal(1500)),new Date(2020-10-23),LocalDate.now(),new BigDecimal(0.18));
        creditCardRepository.saveAll(List.of(creditCard1,creditCard2));
    }

    @AfterEach
    void tearDown() {
    creditCardRepository.deleteAll();
    accountHolderRepository.deleteAll();
    }

    @Test
    void updateBalance() throws Exception {
        creditCard1.setBalance(new Money(new BigDecimal(1500)));
        String body = objectMapper.writeValueAsString(creditCard1);

        MvcResult mvcResult = mockMvc.perform(put("/account/"+ creditCard1.getId()+"/balance")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertEquals(new Money(new BigDecimal(1500)),creditCard1.getBalance());
    }

    @Test
    void transferBalance() {

    }

    @Test
    void thirdPartyTransfer() {
    }
}