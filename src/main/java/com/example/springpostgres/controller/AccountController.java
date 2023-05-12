package com.example.springpostgres.controller;

import com.example.springpostgres.model.Account;
import com.example.springpostgres.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Account>> findAll(
            @RequestParam(required = false, name = "name") String name){
        if(accountService.findAll(name).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(accountService.findAll(name), HttpStatus.OK);
    }

    @GetMapping("/filter/id")
    @ResponseBody
    public ResponseEntity<Optional<Account>> findById(@RequestParam Long id){
        if(accountService.findById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/filter/username")
    @ResponseBody
    public ResponseEntity<Optional<Account>> findByUsername(@RequestParam String username){
        if(accountService.findByUsername(username).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/filter/email")
    @ResponseBody
    public ResponseEntity<Optional<Account>> findByEmail(@RequestParam String email){
        if(accountService.findByEmail(email).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountService.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        return new ResponseEntity<>(accountService.addAccount(account), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId){
        accountService.deleteAccount(accountId);
    }

    @PutMapping(path = "{accountId}")
    public void updateAccount(@PathVariable("accountId") Long accountId,
                              @RequestParam(required = false) String username,
                              @RequestParam(required = false) String email
                              ){
        accountService.updateAccount(accountId,username,email);
    }

}