package com.example.springpostgres.controller;

import com.example.springpostgres.model.Account;
import com.example.springpostgres.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name = "Account", description = "Account management APIs")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(
            summary = "Retrieve all Accounts or the filter ones by name"
    )
    @GetMapping
    public ResponseEntity<List<Account>> findAll(
            @RequestParam(required = false, name = "name") String name){
        if(accountService.findAll(name).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(accountService.findAll(name), HttpStatus.OK);
    }
    @Operation(
            summary = "Retrieve an Accounts by id"
    )
    @GetMapping("/filter/id")
    public ResponseEntity<Optional<Account>> findById(@RequestParam Long id){
        if(accountService.findById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }
    @Operation(
            summary = "Retrieve an Account by username"
    )
    @GetMapping("/filter/username")
    public ResponseEntity<Optional<Account>> findByUsername(@RequestParam String username){
        if(accountService.findByUsername(username).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountService.findByUsername(username), HttpStatus.OK);
    }
    @Operation(
            summary = "Retrieve an Account by email"
    )
    @GetMapping("/filter/email")
    public ResponseEntity<Optional<Account>> findByEmail(@RequestParam String email){
        if(accountService.findByEmail(email).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountService.findByEmail(email), HttpStatus.OK);
    }
    @Operation(
            summary = "Retrieve Accounts by email domain"
    )
    @GetMapping("/filter/domain")
    public ResponseEntity<List<Account>> findByEmailContainsDomain(@RequestParam String domain){
        if(accountService.findByEmailContainsDomain(domain).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(accountService.findByEmailContainsDomain(domain), HttpStatus.OK);
    }
    @Operation(
            summary = "Add a new Account"
    )
    @PostMapping
    public ResponseEntity<HttpStatus> addAccount(@RequestBody Account account){
        try{
            accountService.addAccount(account);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(
            summary = "Delete an Account by id"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id){
        try {
            accountService.deleteById(id);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(
            summary = "Delete all Accounts"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAll(){
        accountService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(
            summary = "Update email and/or username from an Account"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateAccount(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email
    ){
        try{
            accountService.updateById(id,username,email);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
