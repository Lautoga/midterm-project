package com.ironhack.midtermproject.Service.Interfaces;


import com.ironhack.midtermproject.model.ThirdParty;

public interface ThirdPartyService {
    ThirdParty save (ThirdParty thirdParty);

    void delete(Long id);
}
