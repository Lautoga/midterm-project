package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.Service.Interfaces.CreditCardService;
import com.ironhack.midtermproject.controller.DTO.BalanceDTO;
import com.ironhack.midtermproject.controller.interfaces.CreditCardController;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.CreditCard;
import com.ironhack.midtermproject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CreditCardControllerImpl implements CreditCardController {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/credit-card")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    @GetMapping("/credit-card/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard findById(@PathVariable(name = "id")Long id) {
        return creditCardService.findById(id);
    }

    @PostMapping("/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public Account store(@RequestBody @Valid CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }



    @DeleteMapping("/credit-card/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        creditCardService.delete(id);
    }
}

