package com.ironhack.midtermproject.Service.Interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.Savings;

public interface SavingsService {
    Savings save (Savings savings);

    void delete(Long id);

    void updateBalance(Long id, Money balance);
}
