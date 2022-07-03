package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.SavingsService;
import com.ironhack.midtermproject.Utils.Money;
import com.ironhack.midtermproject.model.Savings;
import com.ironhack.midtermproject.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;
    private final Money penaltyFee = new Money(new BigDecimal(40));
    public Savings findById(Long id) {
        //determine if the account exist
        Savings savings = savingsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        //apply the penalty fee if balance is below the minimum
        if (savings.getBalance().getAmount().compareTo(savings.getMinimumBalance().getAmount()) < 0) {
            savings.setBalance(new Money(savings.getBalance().decreaseAmount(penaltyFee)));
        }
        //apply interest rate
        Long yearsBetween = ChronoUnit.YEARS.between(savings.getLastInterestDate(), LocalDate.now());
        if (yearsBetween > 0 && savings.getBalance().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            savings.setBalance(new Money(savings.getBalance().getAmount().add(
                    savings.getBalance().getAmount().multiply(savings.getInterestRate()))));
            savings.setLastInterestDate(LocalDate.now());
        }
        return savings;
    }

    public void delete(Long id) {
        Savings savings =savingsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        savingsRepository.delete(savings);

    }
}
