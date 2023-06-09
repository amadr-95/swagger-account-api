package com.example.spring.config;

import com.example.spring.account.Account;
import com.example.spring.account.AccountRepository;
import com.example.spring.customer.Customer;
import com.example.spring.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DataLoad {

    private String[] names = { "Alice", "Bob", "Charlie", "David", "Emma",
            "Frank", "Grace", "Henry", "Ivy", "Jack" };
    private String[] surnames = { "Smith", "Johnson", null, "Williams", "Brown", "Jones",
            "Miller", "Davis", "Garcia", "Wilson", "Anderson" };

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository, AccountRepository accountRepository){
        return args -> {
            Random r = new Random();
            Customer customer;
            List<Customer> customers = new ArrayList<>();
            int i;
            for (i = 0; i < 30; i++) {
                String name = names[r.nextInt(names.length)];
                String surname = surnames[r.nextInt(surnames.length)];
                String dni = generateRandomDNI();
                String email = generateRandomEmail(name, surname, i);
                String birth = generateRandomBirthDate();
                customer = new Customer(name, surname, dni, email, birth);
                customers.add(customer);
                customerRepository.save(customer);
            }

            for (i = 0; i < 50; i++) {
                Customer randomCustomer = customers.get(r.nextInt(customers.size()));
                BigDecimal balance = new BigDecimal(r.nextDouble() * 100000).setScale(2, BigDecimal.ROUND_HALF_UP);
                accountRepository.save(new Account(balance.doubleValue(), randomCustomer));
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

    private String generateRandomEmail(String name, String surname, int i) {
        String[] domains = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com" };
        Random r = new Random();

        if(surname == null)
            return name.toLowerCase() + "." + i + "@" + domains[r.nextInt(domains.length)];

        return name.toLowerCase() + "." + surname.toLowerCase() + i + "@" + domains[r.nextInt(domains.length)];
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
