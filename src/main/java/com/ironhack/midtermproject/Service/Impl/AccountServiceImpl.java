package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.AccountService;
import com.ironhack.midtermproject.Utils.Money;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void updateBalance(Long id, Money balance) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (!optionalAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
        optionalAccount.get().setBalance(balance);
        accountRepository.save(optionalAccount.get());
    }


    public void transferBalance(Long id1, Long id2, int quantity) {
        Optional<Account> optionalAccount = accountRepository.findById(id1);
        Optional<Account> optionalAccount2 = accountRepository.findById(id2);
        if(quantity<0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Introduce a valid amount");
        }
        if (!optionalAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else if (!optionalAccount2.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else {
            optionalAccount.get().getBalance().decreaseAmount(BigDecimal.valueOf(quantity));
            optionalAccount2.get().getBalance().increaseAmount(BigDecimal.valueOf(quantity));
        }
    }

    @Override
    public void ThirdPartyTransfer(int hashedkey, Long id1, Long id2, int quantity) {
        Optional<Account> optionalAccount = accountRepository.findById(id1);
        Optional<Account> optionalAccount2 = accountRepository.findById(id2);
        if (!optionalAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else if (!optionalAccount2.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else {
            optionalAccount.get().getBalance().decreaseAmount(BigDecimal.valueOf(quantity));
            optionalAccount2.get().getBalance().increaseAmount(BigDecimal.valueOf(quantity));
        }
    }
}
