package com.example.springpostgres.config;

import com.example.springpostgres.model.Account;
import com.example.springpostgres.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AccountConfig {
    private final String[] names = {"Amador", "Sandra", "Pepe", "Jesus", "Sonia", "Rafa"};
    private final String[] domains = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com" };

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository){
        return args -> {
            Random r = new Random();
            for (int i = 1; i <= 20; i++) {
                String name = names[r.nextInt(names.length)];
                String username = name.toLowerCase() + "." + i;
                String email = username + "@" + domains[r.nextInt(domains.length)];
                String password = generateRandomPassword();
                accountRepository.save(new Account(name, username, password, email));
            }
        };
    }

    private String generateRandomPassword() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i <= 8; i++) {
            sb.append(characters.charAt(r.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
