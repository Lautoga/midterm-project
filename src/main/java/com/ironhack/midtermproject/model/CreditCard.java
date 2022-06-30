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
        this.creditLimit = new Money(new BigDecimal(100));
        this.interestRate = new BigDecimal(0.2);
    }

    public CreditCard(Long id, Money balance, AccountHolder primaryOwner,
                      Money creditLimit, BigDecimal interestRate) {
        super(id, balance, primaryOwner);
        this.creditLimit = new Money (new BigDecimal(100));
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
}
