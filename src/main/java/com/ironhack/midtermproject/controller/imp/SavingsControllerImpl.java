package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.controller.interfaces.SavingsController;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.model.Savings;
import com.ironhack.midtermproject.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class SavingsControllerImpl implements SavingsController {

    @Autowired
    private SavingsRepository savingsRepository;
    @GetMapping("/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> findAll() {
        return savingsRepository.findAll();
    }
}
