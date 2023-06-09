package com.example.spring.account;


import com.example.spring.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Accounts",
        description = "Bank Accounts management"
)
@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    //GET
    @Operation(summary = "Retrieve all Accounts")
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> findAll(){
        if(accountService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve all Accounts from a Customer")
    @GetMapping("/customer/{id}/accounts")
    public ResponseEntity<List<Account>> findAllAccountsByCustomerId(@PathVariable Long id){
        return new ResponseEntity<>(accountService.findAllAccountsByCustomerId(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve an Account by id")
    @GetMapping("/accounts/filter/id")
    public ResponseEntity<Account> findById(@RequestParam Long id){
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }
}
