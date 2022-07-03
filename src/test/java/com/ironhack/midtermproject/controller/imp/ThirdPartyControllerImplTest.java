package com.ironhack.midtermproject.controller.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.model.Role;
import com.ironhack.midtermproject.model.ThirdParty;
import com.ironhack.midtermproject.model.User;
import com.ironhack.midtermproject.repository.ThirdPartyRepository;
import com.ironhack.midtermproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class ThirdPartyControllerImplTest {
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User  admin;
    private Role adminRole;
    private ThirdParty thirdParty1;
    @BeforeEach
    void setUp()    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        thirdParty1 = new ThirdParty(1L,"Dan","1234");

   thirdPartyRepository.saveAll(List.of(thirdParty1));
        admin = new User("admin", passwordEncoder.encode("123456"));
        adminRole = new Role("ADMIN", admin);
        admin.setRoles(Set.of(adminRole));

        userRepository.saveAll(List.of(admin));
}
    @AfterEach
    void tearDown() {
        thirdPartyRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void store() throws Exception {
        ThirdParty thirdParty = new ThirdParty(2L,"Fabi","5678");
        String body = objectMapper.writeValueAsString(thirdParty);

        MvcResult mvcResult = mockMvc.perform(
                        post("/third-party")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Fabi"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5678"));

        assertTrue(thirdPartyRepository.existsById(thirdParty.id()));

    }

    @Test
    void delete() throws Exception {
       MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/third-party/" + thirdParty1.id()))
                .andExpect(status().isNoContent())
                .andReturn();
        assertFalse(thirdPartyRepository.existsById(thirdParty1.id()));
    }
}