package com.ironhack.backendProject.services.user;

import com.ironhack.backendProject.models.user.ThirdParty;
import com.ironhack.backendProject.repositories.user.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThirdPartyService {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    //--------------------------GET HASHED KEY---------------------------------------//
    public ThirdParty getThirdPartyByHashKey(String hashKey) {
        Optional<ThirdParty> thirdParty = thirdPartyRepository.findByHashKey(hashKey);
        if (!thirdParty.isPresent()) {
            throw new IllegalArgumentException("Hash key not found");
        }
        return thirdParty.get();
    }
}
