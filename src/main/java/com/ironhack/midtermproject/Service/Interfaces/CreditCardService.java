package com.ironhack.midtermproject.Service.Interfaces;

import com.ironhack.midtermproject.model.CreditCard;

public interface CreditCardService {

    CreditCard findById(Long id);
    void delete(Long id);


}
