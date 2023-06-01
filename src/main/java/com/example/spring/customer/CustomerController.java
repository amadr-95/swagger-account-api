package com.example.spring.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //GET
    @GetMapping("/clients")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    /*@GetMapping("/clients/{id}")
    public Customer findById(@PathVariable Long id) throws CustomerNotFoundException {
        if(customerService.findById(id).isEmpty())
            throw new CustomerNotFoundException("Customer with id "+id+" not found");
        else
            return customerService.findById(id).get();
    }*/

    //POST
    @PostMapping("/clients")
    public void createClient(@RequestBody Customer customer){
        customerService.createClient(customer);
    }
}
