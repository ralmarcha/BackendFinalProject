package com.ironhack.backendProject.services.user;

import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.models.user.Admin;
import com.ironhack.backendProject.models.user.ThirdParty;
import com.ironhack.backendProject.models.user.User;
import com.ironhack.backendProject.services.interfaces.UserServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserServiceInt {

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public AccountHolder createAccountHolder(AccountHolder accountHolder) {
        return null;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return null;
    }

    @Override
    public ThirdParty createThirdParty(ThirdParty thirdParty) {
        return null;
    }
}
