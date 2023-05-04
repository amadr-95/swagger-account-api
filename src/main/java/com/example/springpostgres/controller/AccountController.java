package com.example.springpostgres.controller;

import com.example.springpostgres.entity.Account;
import com.example.springpostgres.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    @ResponseBody()
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/filter/id")
    @ResponseBody()
    public Optional<Account> accountById(@RequestParam Long userId){
        return accountService.findById(userId);
    }

    @GetMapping("/filter/username")
    @ResponseBody()
    public Optional<Account> accountByUsername(@RequestParam String username){
        return accountService.findByUsername(username);
    }

    @GetMapping("/filter/email")
    @ResponseBody
    public Optional<Account> accountByEmail(@RequestParam String email){
        return accountService.findByEmail(email);
    }

    @PostMapping
    public void addAccount(@RequestBody Account account){
        accountService.addNewAccount(account);
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
