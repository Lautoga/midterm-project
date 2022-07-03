package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.CreditCardService;
import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.model.CreditCard;
import com.ironhack.midtermproject.model.Savings;
import com.ironhack.midtermproject.repository.CheckingRepository;
import com.ironhack.midtermproject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
   private CreditCardRepository creditCardRepository;


    @Override
    public CreditCard findById(Long id) {
        //determine if the account exist
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        //apply interest rate
        Long monthsBetween = ChronoUnit.MONTHS.between(creditCard.getLastInterestDate(), LocalDate.now());
        if (monthsBetween > 0 && creditCard.getBalance().getAmount().compareTo(BigDecimal.ZERO) > 0) {
           creditCard.setBalance(new Money(creditCard.getBalance().getAmount().add(
                    creditCard.getBalance().getAmount().multiply(
                            creditCard.getInterestRate().divide(BigDecimal.valueOf(12))))));
           creditCard.setLastInterestDate(LocalDate.now());
        }
        return creditCard;
    }

    public void delete(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        creditCardRepository.delete(creditCard);

    }
}
