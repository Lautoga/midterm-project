package com.ironhack.midtermproject.Service.Interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.Checking;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CheckingService {

    Checking save (Checking checking);
    void update(Long id, Checking checking);
    void delete(Long id);

    void updateBalance(Long id,Money balance);
}
