package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.Service.Interfaces.ThirdPartyService;
import com.ironhack.midtermproject.controller.interfaces.ThirdPartyController;
import com.ironhack.midtermproject.model.ThirdParty;
import com.ironhack.midtermproject.model.User;
import com.ironhack.midtermproject.repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ThirdPartyControllerImpl implements ThirdPartyController {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private ThirdPartyService thirdPartyService;

    @PostMapping("/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty store(@RequestBody @Valid ThirdParty thirdParty) {
        return thirdPartyService.save(thirdParty);
    }

    @DeleteMapping("/third-party/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        thirdPartyService.delete(id);
    }
}
