package com.ironhack.midtermproject.model;


import com.ironhack.midtermproject.classes.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {


    private LocalDate dateOfBirth;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "primary_address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "primary_address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "primary_address_postal_code")),

    })
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mailing_address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "mailing_address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mailing_address_postal_code"))
    })
    private String mailingAddress;

    @OneToMany(mappedBy = "primaryOwner", cascade = CascadeType.ALL)
    private List<Account> accountsPrimaryOwner;

    @OneToMany(mappedBy = "secondaryOwner", cascade = CascadeType.ALL)
    private List<Account> accountsSecondaryOwner;

    public AccountHolder() {
    }

    public AccountHolder(String name, String password, LocalDate dateOfBirth, Address primaryAddress,
                         String mailingAddress, List<Account> accountsPrimaryOwner,
                         List<Account> accountsSecondaryOwner) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.accountsPrimaryOwner = accountsPrimaryOwner;
        this.accountsSecondaryOwner = accountsSecondaryOwner;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getAccountsPrimaryOwner() {
        return accountsPrimaryOwner;
    }

    public void setAccountsPrimaryOwner(List<Account> accountsPrimaryOwner) {
        this.accountsPrimaryOwner = accountsPrimaryOwner;
    }

    public List<Account> getAccountsSecondaryOwner() {
        return accountsSecondaryOwner;
    }

    public void setAccountsSecondaryOwner(List<Account> accountsSecondaryOwner) {
        this.accountsSecondaryOwner = accountsSecondaryOwner;
    }
}