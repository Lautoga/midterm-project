package com.ironhack.midtermproject.controller.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.Utils.Address;
import com.ironhack.midtermproject.Utils.Money;
import com.ironhack.midtermproject.enums.Status;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.model.Role;
import com.ironhack.midtermproject.model.User;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.CheckingRepository;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CheckingControllerImplTest {
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    private User admin;
    private Role adminRole;

    private AccountHolder accountHolder1, accountHolder2;
    private Checking checking1, checking2;
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

        checking1 = new Checking(1L,new Money(new BigDecimal(3000)),accountHolder1,"6798",
                new Money(new BigDecimal(345)),new Money(new BigDecimal(40))
                ,new Date(2020-3-4), Status.ACTIVE);
        checking2 = new Checking(2L,new Money(new BigDecimal(3500)),accountHolder2,"9798",
                new Money(new BigDecimal(300)),new Money(new BigDecimal(40))
                ,new Date(2020-3-4), Status.ACTIVE);
        checkingRepository.saveAll(List.of(checking1,checking2));
    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ana"));
    }

    @Test
    void findById() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        MvcResult mvcResult = mockMvc.perform(get("/checking/"+checking1.getId())
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
        Checking checking = new Checking(4L,new Money(new BigDecimal(3000)),accountHolder,"5678",
                new Money(new BigDecimal(200)),new Money(new BigDecimal(30)), new Date(2006-4-6),Status.ACTIVE);
        String body = objectMapper.writeValueAsString(checking);
        MvcResult mvcResult = mockMvc.perform(
                        post("/courses")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5678"));

        assertTrue(checkingRepository.existsById(checking.getId()));
    }


    @Test
    void delete() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/checking/" + checking1.getId())
                        .headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(checkingRepository.existsById(checking1.getId()));
    }
}