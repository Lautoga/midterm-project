package com.ironhack.midtermproject.controller.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.classes.Address;
import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountControllerImplTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Account account1, account2;
    private AccountHolder accountHolder1, accountHolder2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolder1 = new AccountHolder("Maria","1234",
                LocalDate.of(1988,12,9),new Address("asd","Alicante",03003));
        accountHolder2 = new AccountHolder("Ana","5678",
                LocalDate.of(2000,2,23),new Address("ghj","Valencia",46003));
        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

    }

    @AfterEach
    void tearDown() {
       accountRepository.deleteAll();
    }

    @Test
    void updateBalance() {
    }

    @Test
    void transferBalance() {
    }

    @Test
    void thirdPartyTransfer() {
    }
}