package com.ironhack.backendProject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.backendProject.dto.accounts.CreateCheckingAccountDTO;
import com.ironhack.backendProject.dto.accounts.CreateCreditCardDTO;
import com.ironhack.backendProject.dto.accounts.CreateSavingsDTO;
import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.repositories.account.AccountRepository;
import com.ironhack.backendProject.repositories.user.AccountHolderRepository;
import com.ironhack.backendProject.repositories.user.UserRepository;
import com.ironhack.backendProject.services.user.AdminService;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
public class AdminControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;
   @Autowired
    UserRepository userRepository;
    @Autowired
    AdminService adminService;
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    void tearDown(){
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }
    //-------------------------------------GET ALL ACCOUNTS-----------------------------------------------------------//
    @Test
    @WithMockUser(username = "Raquel", password = "123")
    void getAllAccounts_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO account1 = new CreateCheckingAccountDTO("test", new BigDecimal(500), accountHolder);
        CreateCheckingAccountDTO account2 = new CreateCheckingAccountDTO("test", new BigDecimal(500), accountHolder);
        Account result1 = adminService.createCheckingAccount(account1);
        Account result2 = adminService.createCheckingAccount(account2);

        MvcResult result = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(2, accountRepository.findAll().size());
    }
    //-------------------------------------GET ALL USERS--------------------------------------------------------------//
    @Test
    void getAllUsers_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        AccountHolder accountHolder2 = new AccountHolder("xxx","123",date, address, null);
        accountHolderRepository.save(accountHolder2);

        MvcResult result = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(3, userRepository.findAll().size());
    }
    //---------------------------------------------GET ACCOUNT--------------------------------------------------------//
    @Test
    void getAccountById_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO account1 = new CreateCheckingAccountDTO("test", new BigDecimal(500),accountHolder);
        Account result1 = adminService.createCheckingAccount(account1);

        MvcResult result = mockMvc.perform(get("/account/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("test"));
    }
    //----------------------------------------GET ACCOUNT ID THROWS ERROR---------------------------------------------//
    @Test
    void getAccountById_idNotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/5"))
                .andExpect(status().isNotFound()).andReturn();
        assertEquals("Account not found",result.getResponse().getErrorMessage());
    }

    //--------------------------------------CREATE ACCOUNTS-----------------------------------------------------------//
    @Test
    void createChecking_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO createAccount = new CreateCheckingAccountDTO("test", new BigDecimal(500), accountHolder);

        String body = objectMapper.writeValueAsString(createAccount);

        MvcResult result = mockMvc.perform(post("/create-account/checking").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("test"));
    }

    @Test
    void createSavings_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateSavingsDTO createSavings = new CreateSavingsDTO("test", new BigDecimal(500), accountHolder);
        String body = objectMapper.writeValueAsString(createSavings);

        MvcResult result = mockMvc.perform(post("/create-account/savings").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("test"));
    }

    @Test
    void createCreditCArd_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCreditCardDTO createCreditCard = new CreateCreditCardDTO("test", new BigDecimal(500), accountHolder);
        String body = objectMapper.writeValueAsString(createCreditCard);

        MvcResult result = mockMvc.perform(post("/create-account/credit").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("test"));
    }

    //----------------------------------------------DELETE ACCOUNT----------------------------------------------------//
    @Test
    void deleteAccount_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO account1 = new CreateCheckingAccountDTO("test", new BigDecimal(500), accountHolder);
        CreateCheckingAccountDTO account2 = new CreateCheckingAccountDTO("test", new BigDecimal(500), accountHolder);
        Account result1 = adminService.createCheckingAccount(account1);
        Account result2 = adminService.createCheckingAccount(account2);

        MvcResult result = mockMvc.perform(delete("/delete-account/1"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(1, accountRepository.findAll().size());
    }

    //--------------------------------------------SET BALANCE---------------------------------------------------------//
    @Test
    void setBalance_OK() throws Exception {

        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO account1 = new CreateCheckingAccountDTO("test", new BigDecimal(500), accountHolder);
        Account result1 = adminService.createCheckingAccount(account1);

        MvcResult result = mockMvc.perform(patch("/set-balance/1")
                        .param("balance", "1000"))
                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1000"));
    }

    //--------------------------------------------SET BALANCE ERROR---------------------------------------------------//
    @Test
    void setBalance_ThrowsError() throws Exception {

        MvcResult result = mockMvc.perform(patch("/set-balance/2")
                        .param("balance", "1000"))
                .andExpect(status().isNotFound()).andReturn();

        assertEquals("Account not found",result.getResponse().getErrorMessage());
    }

    //-------------------------------------------GET BALANCE---------------------------------------------------------//
    @Test
    void getBalance_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO account1 = new CreateCheckingAccountDTO("test", new BigDecimal(500), accountHolder);
        Account result1 = adminService.createCheckingAccount(account1);

        MvcResult result = mockMvc.perform(get("/check-balance/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("500"));
    }

    //---------------------------------------------UPDATE ACCOUNT----------------------------------------------------//
    @Test
    void updateAccount_OK() throws Exception {
        PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
        LocalDate date = LocalDate.of(1982,5,10);
        AccountHolder accountHolder = new AccountHolder("Raquel","123",date, address, null);
        accountHolderRepository.save(accountHolder);
        CreateCheckingAccountDTO account1 = new CreateCheckingAccountDTO("test", new BigDecimal(500), accountHolder);
        Account result1 = adminService.createCheckingAccount(account1);

        String body = objectMapper.writeValueAsString(result1);
        MvcResult result = mockMvc.perform(put("/update-checking-account/1")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("test"));
    }
}







