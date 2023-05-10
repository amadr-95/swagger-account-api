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
import java.time.format.DateTimeFormatter;

@Entity
@Table(
        name = "Client",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "client_dni_unique",
                        columnNames = "DNI"
                ),
                @UniqueConstraint(
                        name = "client_email_unique",
                        columnNames = "email"
                )
        }
)
public class Client {
    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "client_sequence",
            strategy = SEQUENCE
    )
    @Column(
            name = "idClient",
            updatable = false
    )
    private Long idClient;

    @Column(
            name = "name",
            nullable = false,
            updatable = false
    )
    private String name;

    @Column(
            name = "surnames",
            /*can be null*/
            updatable = false
    )
    private String surnames;

    @Column(
            name = "DNI",
            nullable = false,
            updatable = false
    )
    private String DNI; //unique

    @Column(
            name = "email",
            nullable = false
            /*can be edited*/
    )
    private String email; //unique

    @Column(
            name = "birth",
            nullable = false,
            updatable = false
    )
    private LocalDate birth;

    /*Constructors*/

    public Client() {
    }

    public Client(String name, String surnames, String DNI, String email, String birth) {
        this.name = name;
        this.surnames = surnames;
        this.DNI = DNI;
        this.email = email;
        this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MMM-dd"));
    }

    /*Getters&Setters*/

    public Long getIdClient() {
        return idClient;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MMM-dd"));
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", DNI='" + DNI + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
