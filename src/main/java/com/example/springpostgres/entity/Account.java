
package com.example.springpostgres.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    /*FK idClient
    *
    *
    *
    *
    * */

    /*Constructors*/

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
        this.creationDate = LocalDate.now();
        //this.creationDate = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MMM-dd")));
        /*
        No es posible cambiar el formato de la fecha siendo tipo DATE porque la BBDD solo acepta el formato yyyy/mm/dd
        Siendo la variable creationDate de tipo String si seria posible
        */
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

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                '}';
    }
}