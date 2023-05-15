
package com.example.springpostgres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;

@Entity
@Table(
        name = "Accounts",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_username", columnNames = "username"),
                @UniqueConstraint(name = "unique_email", columnNames = "email")
        }
)
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
            name = "id",
            updatable = false
    )
    private Long userId;

    @Column(
            name = "name",
            nullable = false,
            updatable = false
    )
    private String name;

    @Column(
            name = "username",
            nullable = false
    )
    private String username; //unique

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "email",
            nullable = false
    )
    private String email; //unique

    @Column(
            name = "creation_date"
    )
    private LocalDate createdOn;

    public Account() {
        this.createdOn = LocalDate.now();
    }

    public Account(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdOn = LocalDate.now();
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = LocalDate.parse(createdOn);
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}