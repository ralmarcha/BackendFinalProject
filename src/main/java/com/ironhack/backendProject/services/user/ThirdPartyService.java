package com.ironhack.backendProject.services.user;

import com.ironhack.backendProject.models.user.ThirdParty;
import com.ironhack.backendProject.repositories.user.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThirdPartyService {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    //--------------------------GET THIRD PARTY BY HASHED KEY--------------------------------//
    public ThirdParty getThirdPartyByHashKey(String hashKey) {
        ThirdParty thirdParty = thirdPartyRepository.findByHashKey(hashKey).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Hash key not found"));
        return thirdParty;
    }
}

