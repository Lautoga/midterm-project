package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.Service.Interfaces.AccountService;
import com.ironhack.midtermproject.controller.DTO.BalanceDTO;
import com.ironhack.midtermproject.controller.interfaces.AccountController;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;


    @PatchMapping("/account/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@PathVariable Long id, @RequestBody @Valid BalanceDTO balanceDTO) {
        accountService.updateBalance(id, balanceDTO.getBalance());
    }

    @PatchMapping("/account/{id}/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferBalance(@PathVariable Long id1,
                                @RequestParam Long id2,
                                @RequestBody @Valid int quantity) {
        accountService.transferBalance(id1,id2,quantity);
    }

    @PatchMapping("/account/transfer/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ThirdPartyTransfer(@PathVariable("key") int hashedKey,
                                   @RequestParam Long id1,
                                   @RequestParam Long id2,
                                   @RequestBody @Valid int quantity) {
         accountService.ThirdPartyTransfer(hashedKey,id1,id2,quantity);
    }


}