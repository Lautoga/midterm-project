package com.ironhack.midtermproject.controller.interfaces;


import com.ironhack.midtermproject.controller.DTO.BalanceDTO;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.Checking;

import java.util.List;

public interface CheckingController {
    List<Checking> findAll();

    Checking findById(Long id);
    Account store(Checking checking);

    void updateBalance(Long id, BalanceDTO checkingBalanceDTO);

    void delete(Long id);

}
