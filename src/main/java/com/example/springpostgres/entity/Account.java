
package com.example.springpostgres.entity;

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

    @Column(
            name = "last_login"
    )
    private LocalDate lastLogin;

    public Account() {
    }

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = String.valueOf(password.hashCode());
        this.email = email;
        this.createdOn = LocalDate.now();
    }

    public Long getUserId() {
        return userId;
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

    public LocalDate getLastLogin() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdOn=" + createdOn +
                ", lastLogin=" + lastLogin +
                '}';
    }
}