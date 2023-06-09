package com.example.spring.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Customers",
        description = "Bank Customers management"
)
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //GET
    @Operation(summary = "Retrieve all Customers or the filter ones by name")
    @GetMapping
    public ResponseEntity<List<Customer>> findAll(@RequestParam(required = false) String name){
        if(customerService.findAll(name).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(customerService.findAll(name), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a Customer by id")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    //POST
    @Operation(summary = "Add new Customer")
    @PostMapping("/new")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.addNewCustomer(customer), HttpStatus.OK);
    }

}
