
package com.example.spring.account;

import com.example.spring.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "account_sequence" //=sequenceName
    )
    @Column(
            name = "idAccount",
            updatable = false
    )
    private Long idAccount;

    @Column(
            name = "balance",
            precision = 2
            /*can be null*/
            /*can be updated*/
    )
    private double balance;

    @Column(
            name = "creationDate",
            nullable = false,
            updatable = false
    )
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idCustomer", //crea la columna idCustomer en la tabla Account que relaciona customer-account
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(
                    name = "customer_id_fk"
            )
    )
    private Customer customer;

    /*Constructors*/

    public Account() {
        //this.creationDate = LocalDate.now();
    }

    public Account(double balance, Customer customer) {
        this.balance = balance;
        this.creationDate = LocalDate.now();
        this.customer = customer;
    }

    /*Getters&Setters*/

    public Long getIdAccount() {
        return idAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                '}';
    }
}