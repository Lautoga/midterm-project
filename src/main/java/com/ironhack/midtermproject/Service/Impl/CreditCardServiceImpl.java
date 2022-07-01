package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.CreditCardService;
import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.CreditCard;
import com.ironhack.midtermproject.repository.CheckingRepository;
import com.ironhack.midtermproject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
   private CreditCardRepository creditCardRepository;

    public CreditCard save(CreditCard creditCard) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }

    @Override
    public void updateBalance(Long id, Money balance) {

    }
}
