package com.example.springpostgres.service;

import com.example.springpostgres.repository.AccountRepository;
import com.example.springpostgres.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //GET
    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long userId){
        return accountRepository.findById(userId);
    }

    public Optional<Account> findByUsername(String username){
        return accountRepository.findByUsername(username);
    }

    public Optional<Account> findByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    //POST
    public void addNewAccount(Account account){
        if(accountRepository.findByUsername(account.getUsername()).isPresent()
        || accountRepository.findByEmail(account.getEmail()).isPresent())
            throw new IllegalArgumentException("user or email are taken");
        accountRepository.save(account);
    }

    //DELETE
    public void deleteAccount(Long accountId){
        if (accountRepository.findById(accountId).isEmpty())
            throw new IllegalArgumentException("user with id "+accountId+" does not exist");
        accountRepository.deleteById(accountId);
    }

    //UPDATE
    public void updateAccount(Long accountId, String username, String email){
        if (accountRepository.findById(accountId).isEmpty())
            throw new IllegalArgumentException("user with id "+accountId+" does not exist");
        Account account = accountRepository.getReferenceById(accountId); //rescatamos la cuenta para editar
        //comprobaciones
        if(username == null || username.isEmpty() || username.equalsIgnoreCase(account.getUsername()))
            throw new IllegalArgumentException("invalid username");
        if(!email.contains("@") || email.equalsIgnoreCase(account.getEmail())){
            //comprobamos que no este en uso por otro user
            if(accountRepository.findByEmail(email).isPresent())
                throw new IllegalArgumentException("email taken");
        }

        account.setUsername(username);
        account.setEmail(email);
    }

}
