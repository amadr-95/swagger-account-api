package com.example.spring.customer;

import com.example.spring.account.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Random;

@Configuration
public class CustomerConfig {

    private String[] names = { "Alice", "Bob", "Charlie", "David", "Emma",
            "Frank", "Grace", "Henry", "Ivy", "Jack" };
    private String[] surnames = { "Smith", "Johnson", null, "Williams", "Brown", "Jones",
            "Miller", "Davis", "Garcia", "Wilson", "Anderson" };

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository, AccountRepository accountRepository){
        return args -> {
            Random r = new Random();
            Customer customer;
            for (int i = 0; i < 30; i++) {
                String name = names[r.nextInt(names.length)];
                String surname = surnames[r.nextInt(surnames.length)];
                String dni = generateRandomDNI();
                String email = generateRandomEmail(name, surname); //comprobar que no este repetido (debido a la aleatoriedad)
                String birth = generateRandomBirthDate();
                customer = new Customer(name, surname, dni, email, birth);
                customerRepository.save(customer);
                //accountRepository.save(new Account(15000, customer));
            }
        };
    }

    private String generateRandomBirthDate() {
        Random r = new Random();

        int year = LocalDate.now().getYear() - r.nextInt(18,50); // Generate year for adult people
        int month = r.nextInt(1,13); // Generate month between 1 and 12
        int day = r.nextInt(1,28); // Generate day between 1 and 28

        return String.valueOf(LocalDate.of(year, month, day));
    }

    private String generateRandomEmail(String name, String surname) {
        String[] domains = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com" };
        Random r = new Random();

        if(surname == null)
            return name.toLowerCase() + "." + "@" + domains[r.nextInt(domains.length)];

        return name.toLowerCase() + "." + surname.toLowerCase() + "@" + domains[r.nextInt(domains.length)];
    }

    private String generateRandomDNI() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            sb.append(r.nextInt(10));
        }
        // Generar una letra aleatoria para el DNI
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        char letra = letras.charAt(r.nextInt(letras.length()));

        // Concatenar la letra al DNI generado
        sb.append(letra);

        return sb.toString();
    }
}
