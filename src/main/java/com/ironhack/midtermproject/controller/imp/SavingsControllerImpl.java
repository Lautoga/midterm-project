package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.Service.Interfaces.SavingsService;
import com.ironhack.midtermproject.controller.DTO.BalanceDTO;
import com.ironhack.midtermproject.controller.interfaces.SavingsController;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.model.CreditCard;
import com.ironhack.midtermproject.model.Savings;
import com.ironhack.midtermproject.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController

public class SavingsControllerImpl implements SavingsController {

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private SavingsService savingsService;

    @GetMapping("/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> findAll() {
        return savingsRepository.findAll();
    }

    @GetMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings findById(@PathVariable(name = "id")Long id) {
        return  savingsService.findById(id);
    }

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Account store(@RequestBody @Valid Savings savings) {
        return savingsRepository.save(savings);
    }




    @DeleteMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        savingsService.delete(id);
    }
}
