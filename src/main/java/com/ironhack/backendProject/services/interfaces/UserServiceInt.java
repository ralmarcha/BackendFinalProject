package com.ironhack.backendProject.services.interfaces;

import com.ironhack.backendProject.dto.users.CreateAccountHolderDTO;
import com.ironhack.backendProject.dto.users.CreateAdminDTO;
import com.ironhack.backendProject.dto.users.CreateThirdPartyDTO;
import com.ironhack.backendProject.models.user.AccountHolder;
import com.ironhack.backendProject.models.user.Admin;
import com.ironhack.backendProject.models.user.ThirdParty;

public interface UserServiceInt {
    AccountHolder createAccountHolder(CreateAccountHolderDTO accountHolderDTO);

    ThirdParty createThirdParty(CreateThirdPartyDTO createThirdPartyDTO);

    Admin createAdmin(CreateAdminDTO adminDTO);
}
