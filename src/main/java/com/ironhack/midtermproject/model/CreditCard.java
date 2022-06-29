package com.ironhack.midtermproject.model;

import com.ironhack.midtermproject.classes.Money;

import javax.persistence.*;
import java.math.BigDecimal;


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




    public CreditCard() {
    }

    public CreditCard(Long id, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                       Money creditLimit, BigDecimal interestRate) {
        super(id, balance, primaryOwner, secondaryOwner);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }


    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {

    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {

    }
}
