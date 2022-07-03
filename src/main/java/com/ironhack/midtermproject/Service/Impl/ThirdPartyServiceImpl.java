package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.ThirdPartyService;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.model.Savings;
import com.ironhack.midtermproject.model.ThirdParty;
import com.ironhack.midtermproject.repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;


    public void delete(Long id) {
        ThirdParty thirdParty = thirdPartyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User"));
        thirdPartyRepository.delete(thirdParty);
    }
}
