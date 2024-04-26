package com.example.swagger.account;

import com.example.swagger.exception.AccountNotFoundException;
import com.example.swagger.exception.UserOrEmailException;
import com.example.swagger.exception.UserOrEmailTaken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //GET
    public List<Account> findAll(String name){
        if (name == null || name.isEmpty())
            return accountRepository.findAll();
        else{
            return accountRepository.findByName(name);
        }
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Does not exist an account with id "+id));
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Does not exist an account with username "+ username));
    }

    public Account findByEmail(String email){
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("Does not exist an account with username "+ email));
    }

    public List<Account> findByEmailContainsDomain(String domain){
        List<Account> accounts = new ArrayList<>();
        for (Account a: accountRepository.findAll()) {
            String mail = a.getEmail().split("@")[1];
            if(mail.equalsIgnoreCase(domain))
                accounts.add(a);
        }
        return accounts;
    }

    /* //lambda expresions
    public List<Account> findByEmailContainsDomain(String domain) {
        return accountRepository.findAll().stream()
                .filter(a -> a.getEmail().split("@")[1].equalsIgnoreCase(domain))
                .collect(Collectors.toList());
    }*/

    //POST
    public Account addAccount(Account account){
        if(accountRepository.findByUsername(account.getUsername()).isPresent()
        || accountRepository.findByEmail(account.getEmail()).isPresent())
            throw new UserOrEmailTaken("Error: User or email is taken");
        if(!account.getEmail().contains("@"))
            throw new UserOrEmailException("Error: Invalid email");
        return accountRepository.save(account);
    }

    //DELETE
    public Account deleteById(Long id){
        Account account = findById(id);
        accountRepository.deleteById(id);
        return account;
    }

    public void deleteAll(){
        accountRepository.deleteAll();
    }

    //UPDATE
    @Transactional
    public Account updateUsernameById(Long id, String username){
        Account account = findById(id);
        if (username == null || username.isEmpty() || username.contains(" ")
                || username.equalsIgnoreCase(account.getUsername())) {
            throw new UserOrEmailException("Error: Invalid username");
        }
        if(accountRepository.findByUsername(username).isPresent())
            throw new UserOrEmailTaken("Error: Username taken");
        account.setUsername(username);
        return account;
    }

    //UPDATE
    @Transactional
    public Account updateEmailById(Long id, String email){
        Account account = findById(id);
        if (email == null || !email.contains("@") || email.contains(" ")
        || email.equalsIgnoreCase(account.getUsername())) {
            throw new UserOrEmailException("Error: Invalid email");
        }
        if(accountRepository.findByUsername(email).isPresent())
            throw new UserOrEmailTaken("Error: Email taken");
        account.setEmail(email);
        return account;
    }
}
