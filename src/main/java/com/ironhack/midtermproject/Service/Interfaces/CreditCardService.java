package com.ironhack.midtermproject.Service.Interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.CreditCard;

public interface CreditCardService {
    CreditCard save (CreditCard creditCard);

    void delete(Long id);

    void updateBalance(Long id, Money balance);
}
