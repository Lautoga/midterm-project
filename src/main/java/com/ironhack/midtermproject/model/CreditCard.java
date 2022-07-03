package com.ironhack.midtermproject.model;

import com.ironhack.midtermproject.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account{

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount"))
    })
   private Money creditLimit;

   private BigDecimal interestRate;

    @NotNull
    private LocalDate creationDate;
    private LocalDate lastInterestDate;


    public CreditCard() {
    }

    public CreditCard(Long id, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                       Money creditLimit,  LocalDate creationDate,LocalDate lastInterestDate,BigDecimal interestRate) {
        super(id, balance, primaryOwner, secondaryOwner);
        this.creditLimit = new Money(new BigDecimal(100));
        this.creationDate = creationDate;
        this.lastInterestDate = lastInterestDate;
        this.interestRate = new BigDecimal(0.2);
    }

    public CreditCard(Long id, Money balance, AccountHolder primaryOwner,
                      Money creditLimit, LocalDate creationDate,LocalDate lastInterestDate, BigDecimal interestRate) {
        super(id, balance, primaryOwner);
        this.creditLimit = new Money (new BigDecimal(100));
        this.creationDate = creationDate;
        this.lastInterestDate = lastInterestDate;
        this.interestRate = new BigDecimal(0.2);
    }


    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if(creditLimit.getAmount().compareTo(BigDecimal.ZERO)<0){
            this.creditLimit = new Money(new BigDecimal(100));
        } else if(creditLimit.getAmount().compareTo(new BigDecimal(100000))>0){
            this.creditLimit = new Money(new BigDecimal(100000));
        }else{
            this.creditLimit = creditLimit;
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(new BigDecimal(0.1))<0){
            this.interestRate =new BigDecimal(0.1);
        }else{
            this.interestRate = interestRate;
        }
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastInterestDate() {
        return lastInterestDate;
    }

    public void setLastInterestDate(LocalDate lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
    }
}
