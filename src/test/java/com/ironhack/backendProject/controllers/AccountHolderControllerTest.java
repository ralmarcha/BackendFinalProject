package com.ironhack.backendProject.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.backendProject.dto.accounts.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.transfers.AccountHolderTransferDTO;
import com.ironhack.backendProject.models.account.Checking;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.services.user.AccountHolderService;
import com.ironhack.backendProject.services.user.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    AccountHolderRepository accountHolderRepository;


    @Autowired
    AdminService adminService;
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //-------------------------------------------CHECK BALANCE --------------------------------------------------------//
    @Test
    void getAuthorById_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder1 = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder1);
        CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(1000).setScale(2), accountHolder1);
        Checking account = (Checking) adminService.createCheckingAccount(createAccountDTO);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("accountId", "1");
        params.add("userId", "1");
               MvcResult result = mockMvc.perform(get("/check-user-balance")
                        .params(params))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1000"));
    }


    //--------------------------------------TRANSFER BETWEEN ACCOUNTS---------------------------------------------//

    @Test
    void transfer_Test() throws Exception {
    PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
    LocalDate date = LocalDate.of(1982,5,10);
    AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
    accountHolderRepository.save(accountHolder);
    CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(1000).setScale(2), accountHolder);
    Checking account = (Checking) adminService.createCheckingAccount(createAccountDTO);
    CreateCheckingAccountDTO createAccountDTO2 = new CreateCheckingAccountDTO("bbb", new BigDecimal(500).setScale(2), accountHolder);
    Checking account2 = (Checking) adminService.createCheckingAccount(createAccountDTO2);

   AccountHolderTransferDTO transferDTO = new AccountHolderTransferDTO(1L, 2L, BigDecimal.valueOf(50), "Raquel");

        String body = objectMapper.writeValueAsString(transferDTO);

        MvcResult result = mockMvc.perform(post("/transfer").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("950"));
    }
}
