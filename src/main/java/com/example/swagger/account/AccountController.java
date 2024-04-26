package com.example.swagger.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
            @RequestParam(required = false) String name){
        if(accountService.findAll(name).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(accountService.findAll(name), HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve an Account by id"
    )
    @GetMapping("/filter/id")
    public ResponseEntity<Account> findById(@RequestParam Long id){
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve an Account by username"
    )
    @GetMapping("/filter/username")
    public ResponseEntity<Account> findByUsername(@RequestParam String username){
        return new ResponseEntity<>(accountService.findByUsername(username), HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve an Account by email"
    )
    @GetMapping("/filter/email")
    public ResponseEntity<Account> findByEmail(@RequestParam String email){
        return new ResponseEntity<>(accountService.findByEmail(email), HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve all Accounts that match with email domain"
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
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        return new ResponseEntity<>(accountService.addAccount(account), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete an Account by id"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Account> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.deleteById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete all Accounts"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll(){
        accountService.deleteAll();
        return new ResponseEntity<>("All accounts deleted",HttpStatus.OK);
    }

    @Operation(
            summary = "Update username from an Account"
    )
    @PutMapping("/update/username/{id}")
    public ResponseEntity<Account> updateUsername(
            @PathVariable Long id,
            @RequestParam String username)
    {
        return new ResponseEntity<>(accountService.updateUsernameById(id, username), HttpStatus.OK);
    }

    @Operation(
            summary = "Update email from an Account"
    )
    @PutMapping("/update/email/{id}")
    public ResponseEntity<Account> updateEmail(
            @PathVariable Long id,
            @RequestParam String email)
    {
        return new ResponseEntity<>(accountService.updateEmailById(id, email), HttpStatus.OK);
    }
}
