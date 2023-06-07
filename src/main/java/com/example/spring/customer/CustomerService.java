package com.example.spring.customer;

import com.example.spring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //GET
    public List<Customer> findAll(String name){
        if(name == null || name.isEmpty())
            return customerRepository.findAll();
        else
            return customerRepository.findByName(name);
    }

    public Customer findById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Does not exist Customer with id "+id));
    }

}
