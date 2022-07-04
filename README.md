# Banking System
Midterm project by Laura Tomás Galiana.

This project is a REST API of a banking system built in Spring Boot and MySQL.

This system has four types of Accounts:

* Checking account

* Student checking

* Savings

* Credit card

and three types of users:

* Account holders

* Admins

* Third party users

The users are allow to do different actions, as shown in the next use case diagram.

![UseCseBanking drawio](https://user-images.githubusercontent.com/105786314/177098692-439c70d9-1388-49c7-b4d8-6c058cb91afb.png)

## Account limitations

Checking accounts:

The Checking account users may be older than 24, otherwise they will have a Student Checking account.
Checking accounts have a minimumBalance of 250 and a monthlyMaintenanceFee of 12.

Credit cards:

Have a default creditLimit of 100.
Are instantiated with a creditLimit between 100 but and 100000, with a default interestRate of 0.2 instantiated between 0.2 and 0.1

Credit cards also have a monthly interest rate.

Savings:

Have a default interest rate of 0.0025 and a maximum 0.5.
Have a default minimumBalance of 1000, and is instantiated with a minimum balance between 1000 and 100.

Saving accounts also have an anual interest rate.

## Users acces

Account holders ccount holders can transfer money from any of their accounts to any other account using Basic Auth. 
They must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.
The transfer should only be processed if the account has sufficient funds.

Third Party users can receive and send money, they must provide their hashed key in the header of the HTTP request, the amount,
the Account id and the account secret key. This type of users only can be added by admins.

## Routes

GET:
* /account-holder           Get all account holders
* /account-holder/{id}      Get one specific account holder
* /admin                    Get all admins
* /admin/{id}               Get one specific admin
* /checking                 Get all checking accounts
* /checking/{id}            Get one specific checking account
* /credit-card              Get all credit card accounts
* /credit-card/{id}         Get one specific credit card account
* /savings                  Get all savings accounts
* /savings/{id}             Get one specific savings account
* /student-checking         Get all student checking accounts
* /student-checking/{id}    Get one specific student checkings account

POST:
* /checking                 Create new checking account
* /credit-card              Create new credit card account
* /savings                  Create new savings account
* /third-party              Create new third party users

PATCH:
* /account/{id}/balance      Modify balance for admin
* /account/{id}/transfer     Transfer founds for account holders
* /account/transfer/{key}    Transfer founds for third parties

DELETE:
* /checking/{id}            Delete checking accounts
* /credit-card/{id}         Delete credit card accounts
* /savings/{id}             Delete savings accounts
* /third-party/{id}         Delete third party users

## Class diagram

![ClassDiagramBanking drawio](https://user-images.githubusercontent.com/105786314/177124052-bda67a9f-32d6-4fdf-8731-fdb9f92cc5f9.png)
