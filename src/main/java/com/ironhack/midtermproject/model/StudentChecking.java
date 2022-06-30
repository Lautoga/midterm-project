package com.ironhack.midtermproject.model;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{
@NotBlank
    private String secretKey;
@NotNull
    private LocalDate creationDate;
@Enumerated(EnumType.STRING)
    private Status status;

    public StudentChecking() {
    }

    public StudentChecking(Long id,Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                            String secretKey, LocalDate creationDate,
                           Status status) {
        super(id, balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = status;
    }

    public StudentChecking(Long id, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(id, balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
    }



    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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
