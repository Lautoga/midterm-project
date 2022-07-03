package com.ironhack.midtermproject.model;

import com.ironhack.midtermproject.Utils.Money;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Embedded
 @AttributeOverrides({
         @AttributeOverride(name = "currency", column = @Column(name = "balance_currency")),
         @AttributeOverride(name = "amount", column = @Column(name = "balance_amount"))
 })
 private Money balance;

 @ManyToOne
 @JoinColumn(name = "primaryOwner_id")
 private AccountHolder primaryOwner;
 @ManyToOne
 @JoinColumn(name = "secondaryOwner_id")
 private AccountHolder secondaryOwner;
 @Embedded
 @AttributeOverrides({
         @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency")),
         @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee_amount"))
 })

 public long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public Account() {
 }

 public Account(Long id, Money balance) {
  this.id = id;
  this.balance = balance;
 }

 public Account(Long id, Money balance, AccountHolder primaryOwner) {
  this.id = id;
  this.balance = balance;
  this.primaryOwner = primaryOwner;
 }

 public Account(Long id, Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {


  this.id = id;
  this.balance = balance;
  this.primaryOwner = primaryOwner;
  this.secondaryOwner = secondaryOwner;
 }

 public Money getBalance() {
  return balance;
 }

 public void setBalance(Money balance) {
  this.balance = balance;
 }

 public AccountHolder getPrimaryOwner() {
  return primaryOwner;
 }

 public void setPrimaryOwner(AccountHolder primaryOwner) {
  this.primaryOwner = primaryOwner;
 }

 public AccountHolder getSecondaryOwner() {
  return secondaryOwner;
 }

 public void setSecondaryOwner(AccountHolder secondaryOwner) {
  this.secondaryOwner = secondaryOwner;
 }




}