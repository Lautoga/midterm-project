package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.ThirdPartyService;
import com.ironhack.midtermproject.model.Savings;
import com.ironhack.midtermproject.model.ThirdParty;
import com.ironhack.midtermproject.repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;


    public ThirdParty save(ThirdParty thirdParty) {
        return null;
    }

    public void delete(Long id) {

    }
}
