package com.ironhack.backendProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.backendProject.dto.accounts.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.transfers.AccountHolderTransferDTO;
import com.ironhack.backendProject.dto.users.CreateAccountHolderDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.account.Checking;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.services.user.AdminService;
import com.ironhack.backendProject.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

    @Autowired
    UserService userService;
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //-------------------------------------------CHECK BALANCE --------------------------------------------------------//
    @Test
    @WithMockUser(username = "user1", password = "pwd")
    void getCheckOwnBalance_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1985,1,1);
        CreateAccountHolderDTO accountHolder = new CreateAccountHolderDTO ("user1", "pwd", address, date);
        AccountHolder ah = userService.createAccountHolder(accountHolder);

        CreateCheckingAccountDTO createAccountDTO = new CreateCheckingAccountDTO("aaa", new BigDecimal(1000).setScale(2), ah);
        Checking account = (Checking) adminService.createCheckingAccount(createAccountDTO);

       MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("accountId", "1");
        params.add("userId", "2");
               MvcResult result = mockMvc.perform(get("/user/check-balance")
                        .params(params))
                .andExpect(status().isOk()).andReturn();
    }
    //-------------------------------------------CHECK OWN ACCOUNT BY ID----------------------------------------------//
    @Test
    @WithMockUser(username = "user1", password = "pwd")
    void getOwnAccount_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("user1","pwd",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO account1 = new CreateCheckingAccountDTO("test", new BigDecimal(500),accountHolder);
        Account result1 = adminService.createCheckingAccount(account1);

        MvcResult result = mockMvc.perform(get("/user/account/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
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
