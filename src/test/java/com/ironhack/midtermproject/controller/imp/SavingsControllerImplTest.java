package com.ironhack.midtermproject.controller.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.Utils.Address;
import com.ironhack.midtermproject.Utils.Money;
import com.ironhack.midtermproject.enums.Status;
import com.ironhack.midtermproject.model.*;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.SavingsRepository;
import com.ironhack.midtermproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SavingsControllerImplTest {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PasswordEncoder passwordEncoder;

    private User admin;
    private Role adminRole;

    @Autowired
    private UserRepository userRepository;

    private AccountHolder accountHolder1, accountHolder2;
    private Savings savings1, savings2;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        admin = new User("admin", passwordEncoder.encode("123456"));
        adminRole = new Role("ADMIN", admin);
        admin.setRoles(Set.of(adminRole));
        accountHolder1 = new AccountHolder("Maria","1234",
                LocalDate.of(1988,12,9),new Address("asd","Alicante",03003));
        accountHolder2 = new AccountHolder("Ana","5678",
                LocalDate.of(2000,2,23),new Address("ghj","Valencia",46003));
        accountHolderRepository.saveAll(List.of(accountHolder1,accountHolder2));

        savings1= new Savings(1L,new Money(new BigDecimal(2000)),accountHolder1,"123345",
                new Money(new BigDecimal(1000)),new BigDecimal(1.5),new Date(2004-23-1),
                LocalDate.of(2022,3,4), Status.ACTIVE);
        savings2= new Savings(2L,new Money(new BigDecimal(2500)),accountHolder2,"146745",
                new Money(new BigDecimal(900)),new BigDecimal(1.2),new Date(2008-21-1),
                LocalDate.of(2022,5,23), Status.ACTIVE);
        savingsRepository.saveAll(List.of(savings1,savings2));
    }


    @AfterEach
    void tearDown() {
        savingsRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/savings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("123345"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("146745"));
    }
    @Test
    void findById() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        MvcResult mvcResult = mockMvc.perform(get("/savings/"+savings1.getId())
                        .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Ana"));
    }
    @Test
    void store() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        AccountHolder accountHolder = new AccountHolder("Maria","1234",
             LocalDate.of(1988,12,9),new Address("asd","Alicante",03003));

       Savings savings =new Savings(2L,new Money(new BigDecimal(2500)), accountHolder,"145675",
               new Money(new BigDecimal(1000)),new BigDecimal(1.0),new Date(2018-21-1),
               LocalDate.of(2022,5,23), Status.ACTIVE);
        String body = objectMapper.writeValueAsString(savings);


        MvcResult mvcResult = mockMvc.perform(
                        post("/savings")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1234"));


        assertTrue(savingsRepository.existsById(savings.getId()));
    }

    @Test
    void delete() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/savings/{id}" +savings1.getId())
                        .headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(savingsRepository.existsById(savings1.getId()));
    }


}