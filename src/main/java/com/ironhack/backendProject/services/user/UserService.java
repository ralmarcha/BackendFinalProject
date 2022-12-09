package com.ironhack.backendProject.services.user;

import com.ironhack.backendProject.dto.users.CreateAccountHolderDTO;
import com.ironhack.backendProject.dto.users.CreateAdminDTO;
import com.ironhack.backendProject.dto.users.CreateThirdPartyDTO;
import com.ironhack.backendProject.models.user.*;
import com.ironhack.backendProject.repositories.user.*;
import com.ironhack.backendProject.services.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService implements UserServiceInt {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;


    //------------------------------CREATE ACCOUNT HOLDER-----------------------------------------------------//
    public AccountHolder createAccountHolder(CreateAccountHolderDTO accountHolderDTO){
        String encodedPassword = passwordEncoder.encode(accountHolderDTO.getPassword());
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setPassword(encodedPassword);
        accountHolder.setUsername(accountHolderDTO.getUsername());
        accountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());
        accountHolder.setPrimaryAddress(accountHolderDTO.getAddress());
        userRepository.save(accountHolder);
        Role role = roleRepository.save(new Role("ACCOUNT_HOLDER", accountHolder));
        return accountHolder;
    }

    //------------------------------CREATE THIRD PARTY-------------------------------------------------------//
    public ThirdParty createThirdParty(CreateThirdPartyDTO createThirdPartyDTO) {
        String encodedPassword = passwordEncoder.encode(createThirdPartyDTO.getHashKey());
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setHashKey(encodedPassword);
        thirdParty.setName(createThirdPartyDTO.getName());
        userRepository.save(thirdParty);
        Role role = roleRepository.save(new Role("THIRD_PARTY", thirdParty));
        return thirdParty;
    }

    //------------------------------------CREATE ADMIN--------------------------------------------------------//
    public Admin createAdmin(CreateAdminDTO adminDTO) {
        String encodedPassword = passwordEncoder.encode(adminDTO.getPassword());
        Admin admin= new Admin();
        admin.setPassword(encodedPassword);
        admin.setUsername(adminDTO.getUsername());
        userRepository.save(admin);
        Role role = roleRepository.save(new Role("ADMIN", admin));
       return admin;
    }
    //---------------------------------GET HASH KEY----------------------------------------------------------//
    public ThirdParty getThirdPartyByHashKey(String hashKey) {
        return thirdPartyRepository.findByHashKey(hashKey).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Hash key not found"));
    }
}

