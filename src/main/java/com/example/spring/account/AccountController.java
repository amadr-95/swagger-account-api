package com.example.spring.account;


import com.example.spring.customer.Customer;
import com.example.spring.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Tag(
        name = "Accounts",
        description = "Bank Accounts management"
)
@RestController
@RequestMapping("/api/accounts")
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
    @GetMapping
    public ResponseEntity<List<Account>> findAll(){
        if(accountService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve all Accounts from a Customer")
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Account>> findAllAccountsByCustomerId(@PathVariable Long id){
        if(accountService.findAllAccountsByCustomerId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(accountService.findAllAccountsByCustomerId(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve an Account by id")
    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    //POST
    /*@Operation(summary = "Add an Account to a Customer")
    @PostMapping("/new/customer/{id}")
    public ResponseEntity<Account> addNewAccountToACustomer(@RequestBody Account account, @PathVariable Long id){
        return new ResponseEntity<>(accountService.addNewAccountToACustomer(id, account), HttpStatus.OK);
    }*/

    @Operation(summary = "Add one or many Accounts to a Customer")
    @PostMapping("/new/list/customer/{id}")
    public ResponseEntity<List<Account>> addNewListAccountsToACustomer(@RequestBody List<Account> accounts, @PathVariable Long id){
        return new ResponseEntity<>(accountService.addNewListAccountsToACustomer(id, accounts), HttpStatus.OK);
    }

    //PUT
    @Operation(summary = "Update balance from an Account")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Account> updateAccountBalance(@PathVariable Long id, @RequestParam BigDecimal balance){
        return new ResponseEntity<>(accountService.updateAccountBalance(id, balance), HttpStatus.OK);
    }

}
