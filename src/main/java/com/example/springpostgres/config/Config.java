package com.example.springpostgres.config;

import com.example.springpostgres.entity.Client;
import com.example.springpostgres.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Random;

@Configuration
public class Config {

    private String[] names = { "Alice", "Bob", "Charlie", "David", "Emma",
            "Frank", "Grace", "Henry", "Ivy", "Jack" };
    private String[] surnames = { "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Miller", "Davis", "Garcia", "Wilson", "Anderson" };

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository){
        return args -> {
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                String name = names[r.nextInt(names.length)];
                String surname = surnames[r.nextInt(surnames.length)];
                String dni = generateRandomDNI();
                String email = generateRandomEmail();
                String birth = generateRandomBirthDate();
                //clientRepository.save(new Client(name, surname, dni, email, birth));
            }

            /*for (int i = 1; i <= 10; i++) {
                accountRepository.save(new Account(r.nextDouble(0, 100000)));
            }*/
        };
    }

    /*@Bean
    CommandLineRunner commandLineRunnerClient(ClientRepository clientRepository){
        return args -> {
            Random r = new Random();

            for (int i = 0; i < 10; i++) {
                String name = names[r.nextInt(names.length)];
                String surname = surnames[r.nextInt(surnames.length)];
                String DNI = generateRandomDNI();
                String email = generateRandomEmail();
                String birth = generateRandomBirthDate();
                clientRepository.save(new Client(name, surname, DNI, email, birth));
            }
        };
    }*/

    private String generateRandomBirthDate() {
        Random r = new Random();

        int year = LocalDate.now().getYear() - r.nextInt(18,50); // Generate year for adult people
        int month = r.nextInt(1,13); // Generate month between 1 and 12
        int day = r.nextInt(1,28); // Generate day between 1 and 28

        return String.valueOf(LocalDate.of(year, month, day));
    }

    private String generateRandomEmail() {
        String[] domains = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com" };

        Random r = new Random();

        return names[r.nextInt(names.length)].toLowerCase() +
                "." + surnames[r.nextInt(surnames.length)].toLowerCase() +
                "@" + domains[r.nextInt(domains.length)];
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
