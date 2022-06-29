DROP SCHEMA IF EXISTS midterm;
CREATE SCHEMA midterm;
USE midterm;

CREATE TABLE user(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
password VARCHAR(255),
PRIMARY KEY(id)
);

CREATE TABLE role(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
user_id BIGINT,
PRIMARY KEY(id),
FOREIGN KEY(user_id) REFERENCES user(id)
);

CREATE TABLE account_holder(
     id BIGINT,
     date_of_birth DATE,
	 primary_address_street VARCHAR(255),
	 primary_address_city VARCHAR(255),
	 primary_address_postal_code INT,
     mailing_address_street VARCHAR(255),
	 mailing_address_city VARCHAR(255),
	 mailing_address_postal_code INT,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES user(id)
    );
    
    CREATE TABLE Admin (
	id BIGINT,
    name VARCHAR(255),
	PRIMARY KEY (id),
    FOREIGN KEY(id) REFERENCES user(id)
);

CREATE TABLE account(
id BIGINT NOT NULL AUTO_INCREMENT,
balance_amount  DECIMAL,
balance_currency  VARCHAR(255) DEFAULT 'USD',
primary_owner_id BIGINT,
secondary_owner_id BIGINT,
penalty_fee_amount DECIMAL DEFAULT 0,
penalty_fee_currency VARCHAR(255) DEFAULT 'USD',
PRIMARY KEY(id),
FOREIGN KEY(primary_owner_id) REFERENCES account_holder(id),
FOREIGN KEY(secondary_owner_id) REFERENCES account_holder(id)
    );


CREATE TABLE Checking (
	id BIGINT NOT NULL AUTO_INCREMENT, 
    secret_key VARCHAR(255),    
    minimum_balance_amount DECIMAL DEFAULT 250,
    minimum_balance_currency  VARCHAR(255) DEFAULT 'USD',
    monthly_maintenance_fee_amount DECIMAL DEFAULT 120,
    monthly_maintenance_fee_currency  VARCHAR(255) DEFAULT 'USD',
    creation_date DATE,
    status VARCHAR(6),
  	PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account (id)
);
   
   
 CREATE TABLE Credit_card (
	id BIGINT AUTO_INCREMENT NOT NULL,
    monthly_maintenance_fee_amount DECIMAL DEFAULT 120,
    monthly_maintenance_fee_currency  VARCHAR(255) DEFAULT 'USD',
    credit_limit_amount DECIMAL  DEFAULT 0,
    credit_limit_currency VARCHAR(255) DEFAULT 'USD',
    interest_rate DECIMAL,
  	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES account (id)
);
  
  
  
CREATE TABLE Savings (
	id BIGINT AUTO_INCREMENT NOT NULL,
    secret_key VARCHAR(255),
    minimum_balance_amount DECIMAL DEFAULT 250,
    minimum_balance_currency  VARCHAR(255) DEFAULT 'USD',
    interest_rate DECIMAL,
    creation_date DATE,
    status VARCHAR(6),
  	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES account (id)
);

   CREATE TABLE Student_Checking (
	id BIGINT NOT NULL AUTO_INCREMENT,
    secret_key VARCHAR(255),
	creation_date DATE,
    status VARCHAR(6),
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES account (id)
);  

  CREATE TABLE third_party (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    hashed_key VARCHAR(255),
	PRIMARY KEY (id)
);  