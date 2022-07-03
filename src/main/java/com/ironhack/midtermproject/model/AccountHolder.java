package com.ironhack.midtermproject.model;


import com.ironhack.midtermproject.Utils.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
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
    private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner", cascade = CascadeType.ALL)
    private List<Account> accountPrimaryOwner;

    @OneToMany(mappedBy = "secondaryOwner", cascade = CascadeType.ALL)
    private List<Account> accountSecondaryOwner;

    public AccountHolder() {
    }

    public AccountHolder(String name, String password, LocalDate dateOfBirth,
                         Address primaryAddress, Address mailingAddress) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;

    }


    public AccountHolder(String name, String password,LocalDate dateOfBirth, Address primaryAddress) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
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

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getAccountPrimaryOwner() {
        return accountPrimaryOwner;
    }

    public void setAccountsPrimaryOwner(List<Account> accountsPrimaryOwner) {
        this.accountPrimaryOwner = accountsPrimaryOwner;
    }

    public List<Account> getAccountSecondaryOwner() {
        return accountSecondaryOwner;
    }

    public void setAccountSecondaryOwner(List<Account> accountSecondaryOwner) {
        this.accountSecondaryOwner = accountSecondaryOwner;
    }

}