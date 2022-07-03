package com.ironhack.midtermproject.controller.interfaces;


import com.ironhack.midtermproject.model.AccountHolder;


import java.util.List;

public interface AccountHolderController {
    List<AccountHolder> findAll();
    AccountHolder findById(Long id);
}
