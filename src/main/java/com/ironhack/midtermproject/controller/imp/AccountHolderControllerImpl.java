package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.controller.interfaces.AccountHolderController;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
    @Autowired
    private AccountHolderRepository accountHolderRepository;


        @GetMapping("/account-holder")
        @ResponseStatus(HttpStatus.OK)
        public List<AccountHolder> findAll() {
            return accountHolderRepository.findAll();
        }


    @GetMapping("/account-holder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder findById(@PathVariable(name = "id") Long id) {
        Optional<AccountHolder> optionalAccountHolder = accountHolderRepository.findById(id);
        return optionalAccountHolder.get();
    }
}
