package com.example.spring.customer;

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
        name = "Customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customer_dni_unique",
                        columnNames = "DNI"
                ),
                @UniqueConstraint(
                        name = "customer_email_unique",
                        columnNames = "email"
                )
        }
)
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "customer_sequence",
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
    private String dni; //unique

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

    public Customer() {
    }

    public Customer(String name, String surnames, String dni, String email, String birth) {
        this.name = name;
        this.surnames = surnames;
        this.dni = dni;
        this.email = email;
        this.birth = LocalDate.parse(birth);
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
        this.birth = LocalDate.parse(birth);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
