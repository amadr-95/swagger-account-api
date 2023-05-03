package com.example.springpostgres.config;

import com.example.springpostgres.entity.Account;
import com.example.springpostgres.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository){
        return args -> {
            List<Account> accounts = new ArrayList<>();
            String user, pass, email;
            for (int i = 1; i <= 10; i++) {
                user = "user"+i;
                pass = "password"+i;
                email = user+"@gmail.com";
                accounts.add(new Account(user,pass,email));
            }
            accountRepository.saveAll(accounts);
        };
    }
}
