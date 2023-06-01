package com.example.spring.account;

import com.example.spring.account.Account;
import com.example.spring.account.AccountService;
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
            summary = "Retrieve an Account by id"
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
    public ResponseEntity<String> addAccount(@RequestBody Account account){
        try{
            accountService.addAccount(account);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account succesfully created", HttpStatus.CREATED);
    }
    @Operation(
            summary = "Delete an Account by id"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        try {
            accountService.deleteById(id);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Account succesfully deleted", HttpStatus.OK);
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
    public ResponseEntity<String> updateUsername(
            @PathVariable("id") Long id,
            @RequestParam String username)
    {
        try{
            accountService.updateUsernameById(id,username);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Username updated", HttpStatus.OK);
    }

    @Operation(
            summary = "Update email from an Account"
    )
    @PutMapping("/update/email/{id}")
    public ResponseEntity<String> updateEmail(
            @PathVariable("id") Long id,
            @RequestParam String email)
    {
        try{
            accountService.updateEmailById(id,email);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Email updated", HttpStatus.OK);
    }

}
