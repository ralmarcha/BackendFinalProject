package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.dto.users.CreateAccountHolderDTO;
import com.ironhack.backendProject.dto.users.CreateAdminDTO;
import com.ironhack.backendProject.dto.users.CreateThirdPartyDTO;
import com.ironhack.backendProject.models.user.*;
import com.ironhack.backendProject.services.user.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    //------------------------------CREATE ACCOUNT HOLDER----------------------------------//
    @PostMapping("/create-account-holder")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody CreateAccountHolderDTO accountHolderDTO){
        return userService.createAccountHolder(accountHolderDTO);
    }

    //------------------------------CREATE THIRD PARTY----------------------------------//
    @PostMapping("/create-third-party")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody CreateThirdPartyDTO createThirdPartyDTO) {
        return userService.createThirdParty(createThirdPartyDTO);
    }

    //------------------------------CREATE ADMIN-----------------------------------------//
    @PostMapping("/create-admin")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Admin createAdmin(@RequestBody CreateAdminDTO adminDTO) {
        return userService.createAdmin(adminDTO);
    }

    }

