package com.ironhack.midtermproject.controller.interfaces;


import com.ironhack.midtermproject.controller.DTO.BalanceDTO;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.CreditCard;

import java.util.List;

public interface CreditCardController {
    List<CreditCard> findAll();
    CreditCard findById(Long id);
    Account store(CreditCard creditCard);


    void updateBalance(Long id, BalanceDTO creditCardBalanceDTO);

    void delete(Long id);
}
