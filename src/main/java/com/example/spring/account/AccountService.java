package com.example.spring.account;

import com.example.spring.customer.Customer;
import com.example.spring.customer.CustomerRepository;
import com.example.spring.exception.ResourceInvalidException;
import com.example.spring.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    //GET
    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public List<Account> findAllAccountsByCustomerId(Long id){
        if(!customerRepository.existsById(id))
            throw new ResourceNotFoundException("Does not exist Customer with id "+id);
        return accountRepository.findAllAccountsByCustomerId(id);
    }

    public Account findById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Does not exist an Account with id "+id));
    }

    //POST
    /*public Account addNewAccountToACustomer(Long id, Account account){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Does not exist a Customer with id "+id));
        Account acc = new Account(account.getBalance(), customer);
        return accountRepository.save(acc);
    }*/

    public List<Account> addNewListAccountsToACustomer(Long id, List<Account> accounts){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Does not exist a Customer with id "+id));

        List<Account> accList = new ArrayList<>(accounts.size());
        for (Account a: accounts) {
            accList.add(new Account(a.getBalance(), customer));
        }
        return accountRepository.saveAll(accList);
    }

    //PUT
    @Transactional
    public Account updateAccountBalance(Long id, BigDecimal balance){
        Account account = findById(id);
        if(balance.doubleValue() < 0)
            throw new ResourceInvalidException("Balance can not be negative");
        account.setBalance(balance.doubleValue());
        return account;
    }

    @Transactional
    public Account addQuantityToAnAccount(Long id, BigDecimal quantity){
        Account account = findById(id);
        //TO DO

        return account;
    }


}
