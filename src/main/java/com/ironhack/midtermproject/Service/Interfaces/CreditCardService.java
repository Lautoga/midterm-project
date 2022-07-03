package com.ironhack.midtermproject.Service.Interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.CreditCard;
import com.ironhack.midtermproject.model.Savings;

public interface CreditCardService {

    CreditCard findById(Long id);
    void delete(Long id);


}
