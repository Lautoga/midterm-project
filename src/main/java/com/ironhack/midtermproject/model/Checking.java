package com.ironhack.midtermproject.model;

import com.ironhack.midtermproject.Utils.Money;
import com.ironhack.midtermproject.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{
   @NotBlank
   private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount"))
    })
   private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount"))
    })
   private Money monthlyMaintenanceFee;

    @NotNull
   private Date creationDate;

    @Enumerated(EnumType.STRING)
   private Status status;



    public Checking() {
    }

    public Checking(Long id, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                     String secretKey, Money minimumBalance,
                    Money monthlyMaintenanceFee, Date creationDate, Status status) {
        super(id, balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.creationDate = creationDate;
        this.status = status;
    }

    public Checking(Long id, Money balance, AccountHolder primaryOwner, String secretKey,
                    Money minimumBalance, Money monthlyMaintenanceFee, Date creationDate, Status status) {
        super(id, balance, primaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
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

    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {

    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
