package com.ironhack.backendProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.backendProject.dto.users.CreateAccountHolderDTO;
import com.ironhack.backendProject.dto.users.CreateAdminDTO;
import com.ironhack.backendProject.dto.users.CreateThirdPartyDTO;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    AccountHolderRepository accountHolderRepository;


    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //------------------------------CREATE ACCOUNT HOLDER-------------------------------------------------------------//
    @Test
    void createAccountHolder_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(2002,1,1);
        CreateAccountHolderDTO accountHolder = new CreateAccountHolderDTO ("Negu", "123", address, date);
        String body = objectMapper.writeValueAsString(accountHolder);
        MvcResult result = mockMvc.perform(post("/create-account-holder").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Negu"));
    }
    //------------------------------CREATE ADMIN----------------------------------------------------------------------//
    @Test
    void createAdmin_OK() throws Exception {
        CreateAdminDTO admin = new CreateAdminDTO("Negu", "123");
        String body = objectMapper.writeValueAsString(admin);
        MvcResult result = mockMvc.perform(post("/create-admin").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Negu"));
    }

    //------------------------------CREATE THIRD PARTY---------------------------------------------------------------//
    @Test
    void createThirdParty_OK() throws Exception {
    CreateThirdPartyDTO thirdParty = new CreateThirdPartyDTO("abc", "becu");
        String body = objectMapper.writeValueAsString(thirdParty);
        MvcResult result = mockMvc.perform(post("/create-third-party").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("becu"));
    }

    //------------------------------MODIFY PASSWORD-------------------------------------------------------------------//
    @Test
    @WithMockUser(username = "user1", password = "pwd")
    public void modifyPassword_authorized() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes", 8330, "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("user1", "pwd", date, address, null);
        accountHolderRepository.save(accountHolder);

        MvcResult result = mockMvc.perform(patch("/modify-password")
                        .param("password", "000"))
                .andExpect(status().isOk()).andReturn();
    }}

