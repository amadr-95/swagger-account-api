package com.example.spring.account;

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

    /*public List<Account> findAllAccountsByCustomerId(Long idCustomer){
        if(!customerRepository.existsById(idCustomer))
            throw new ResourceNotFoundException("Does not exist Customer with id "+idCustomer);
        return accountRepository.findByCustomerId(idCustomer);
    }*/

    public Account findById(Long idAccount){
        return accountRepository.findById(idAccount)
                .orElseThrow(() -> new ResourceNotFoundException("Does not exist an Account with id "+idAccount));
    }


}
