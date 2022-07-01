package com.ironhack.midtermproject.controller.interfaces;


import com.ironhack.midtermproject.model.ThirdParty;


public interface ThirdPartyController {
    ThirdParty store(ThirdParty thirdParty);

    void delete(Long id);
}
