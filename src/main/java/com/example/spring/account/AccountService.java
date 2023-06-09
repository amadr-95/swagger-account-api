package com.example.spring.account;

import com.example.spring.customer.Customer;
import com.example.spring.customer.CustomerRepository;
import com.example.spring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Account addNewAccountToACustomer(Long id, Account account){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Does not exist a Customer with id "+id));
        Account acc = new Account(account.getBalance(), customer);
        return accountRepository.save(acc);
    }


}
