package com.example.springpostgres.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "client", //mismo nombre que el de la propiedad de tipo Client definida en la clase Account
            cascade = CascadeType.ALL //esto hace que cuando se persista un cliente, tambien lo hagan sus entidades hijas (cuentas)
    )
    /*Esta propiedad no es un campo de la BBDD (entre otras cosas porque no se puede
    guardar una lista en una BBDD relacional), pero si podremos acceder a la lista de cuentas
    de cada cliente mediantes consultas*/
    private List<Account> accounts;

    /*Constructors*/

    public Client() {

    }

    public Client(String name, String surnames, String dni, String email, String birth) {
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", DNI='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
