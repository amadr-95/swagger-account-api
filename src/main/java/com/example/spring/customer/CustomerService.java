package com.example.spring.customer;

import com.example.spring.exception.ResourceInvalidException;
import com.example.spring.exception.ResourceNotFoundException;
import com.example.spring.exception.ResourceTakenException;
import jakarta.transaction.Transactional;
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

    //POST
    public Customer addNewCustomer(Customer customer){
        if(customerRepository.findByDni(customer.getDni()).isPresent()) {
            throw new ResourceTakenException("DNI '" +customer.getDni() + "' already exists");
        }else if(customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new ResourceTakenException("Email '" + customer.getEmail() + "' already exists");
        }
        return customerRepository.save(customer);
    }

    //PUT
    @Transactional
    public Customer updateCustomerEmail(Long id, String email){
        Customer customer = findById(id);
        if(email.isEmpty() || !email.contains("@"))
            throw new ResourceInvalidException("Error: '@' is missing");
        if(customerRepository.findByEmail(email).isPresent()){
            throw new ResourceTakenException("Email '"+email+"' is already in use");
        }
        customer.setEmail(email);
        return customer;
    }

}
