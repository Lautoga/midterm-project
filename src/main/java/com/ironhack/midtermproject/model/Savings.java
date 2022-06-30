package com.ironhack.midtermproject.model;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account{

@NotBlank
private String secretKey;
@Embedded
@AttributeOverrides({
        @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
        @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount"))
})
private Money minimumBalance;
@NotNull
    @DecimalMin("0.0")
    @DecimalMax("0.5")
private BigDecimal interestRate;
    @NotNull

private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
private Status status;


    public Savings() {
    }

    public Savings(Long id, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                    String secretKey, Money minimumBalance, BigDecimal interestRate,
                   LocalDate creationDate, Status status) {
        super(id, balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = new Money(new BigDecimal(1000));
        this.interestRate =new BigDecimal(0.0025);
        this.creationDate = creationDate;
        this.status = status;
    }

    public Savings(Long id, Money balance, AccountHolder primaryOwner, String secretKey,
                   Money minimumBalance, BigDecimal interestRate, LocalDate creationDate, Status status) {
        super(id, balance, primaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance =new Money(new BigDecimal(1000)) ;
        this.interestRate = new BigDecimal(0.0025);
        this.creationDate = creationDate;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }


    public void setMinimumBalance(Money minimumBalance) {
        if(minimumBalance.getAmount().compareTo(BigDecimal.ZERO)<0){
            this.minimumBalance = new Money(new BigDecimal(1000));
        } else if(minimumBalance.getAmount().compareTo(new BigDecimal(100))<0){
            this.minimumBalance = new Money(new BigDecimal(100));
        }else{
            this.minimumBalance = minimumBalance;
        }

    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(BigDecimal.ZERO)<0){
            this.interestRate =new BigDecimal(0.0025);
        } else if(interestRate.compareTo(new BigDecimal(0.0025))>0){
            this.interestRate = new BigDecimal(0.0025);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
