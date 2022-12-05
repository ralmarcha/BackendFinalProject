package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.models.user.Admin;
import com.ironhack.backendProject.models.user.ThirdParty;
import com.ironhack.backendProject.models.user.User;

import java.util.List;

public interface AdminServiceInt {

    List<User> getAllUsers();
    AccountHolder createAccountHolder(AccountHolder accountHolder);
    Admin createAdmin(Admin admin);
    ThirdParty createThirdParty(ThirdParty thirdParty);
    //void deleteById(Long id);

}
