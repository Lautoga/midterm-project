package com.ironhack.midtermproject.Service.Interfaces;


import com.ironhack.midtermproject.model.Savings;

public interface SavingsService {

    Savings findById(Long id);
    void delete(Long id);


}
