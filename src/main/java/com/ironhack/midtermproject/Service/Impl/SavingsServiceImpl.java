package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.SavingsService;
import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.Savings;
import com.ironhack.midtermproject.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;

    public Savings save(Savings savings) {
        return null;
    }


    public void delete(Long id) {

    }


    public void updateBalance(Long id, Money balance) {

    }
}
