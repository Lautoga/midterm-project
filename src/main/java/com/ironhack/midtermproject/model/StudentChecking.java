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
public class StudentChecking extends Account{
@NotBlank
    private String secretKey;
@NotNull
    private Date creationDate;
@Enumerated(EnumType.STRING)
    private Status status;

    public StudentChecking() {
    }

    public StudentChecking(Long id,Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                            String secretKey, Date creationDate, Status status) {
        super(id, balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = status;
    }

    public StudentChecking(Long id, Money balance, AccountHolder primaryOwner, String secretKey,
                           Date creationDate, Status status) {
        super(id, balance, primaryOwner);
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = status;
    }



    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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
