package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.controller.DTO.BalanceDTO;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.CreditCard;
import com.ironhack.midtermproject.model.Savings;

import java.util.List;

public interface SavingsController {
    List<Savings> findAll();
    Savings findById(Long id);
    Account store(Savings savings);


    void delete(Long id);
}
