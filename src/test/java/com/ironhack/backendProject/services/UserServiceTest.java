package com.ironhack.backendProject.services;

import com.ironhack.backendProject.dto.users.CreateAccountHolderDTO;
import com.ironhack.backendProject.dto.users.CreateAdminDTO;
import com.ironhack.backendProject.dto.users.CreateThirdPartyDTO;
import com.ironhack.backendProject.models.embeddeds.PrimaryAddress;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.models.user.Admin;
import com.ironhack.backendProject.models.user.ThirdParty;
import com.ironhack.backendProject.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;


    //------------------------------CREATE ACCOUNT HOLDER---------------------------------//
    @Test
    public void shouldCreateAccountHolder_Test(){
       PrimaryAddress address = new PrimaryAddress("c/Lesmes",8330 , "Barcelona", "Spain");
       LocalDate date = LocalDate.of(2002,1,1);
       CreateAccountHolderDTO accountHolder = new CreateAccountHolderDTO ("Negu", "123", address, date);
       AccountHolder result = userService.createAccountHolder(accountHolder);

       assertEquals("Negu", result.getUsername());

    }
    //------------------------------CREATE THIRD PARTY------------------------------------//
    @Test
    public void shouldCreateThirdParty_Test(){
        CreateThirdPartyDTO thirdParty = new CreateThirdPartyDTO("abc", "becu");
        ThirdParty result = userService.createThirdParty(thirdParty);

        assertEquals("becu", result.getName());
    }

    //------------------------------CREATE ADMIN------------------------------------------//
    @Test
    public void shouldCreateAdmin_Test(){
        CreateAdminDTO admin = new CreateAdminDTO("Negu", "123");
        Admin result = userService.createAdmin(admin);

        assertEquals("Negu", result.getUsername());
    }
}
