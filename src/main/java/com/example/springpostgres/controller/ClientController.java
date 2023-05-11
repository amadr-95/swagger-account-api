package com.example.springpostgres.controller;

import com.example.springpostgres.exception.ClientNotFoundException;
import com.example.springpostgres.model.Client;
import com.example.springpostgres.repository.ClientRepository;
import com.example.springpostgres.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //GET
    @GetMapping("/clients")
    public List<Client> findAll(){
        return clientService.findAll();
    }

    /*@GetMapping("/clients/{id}")
    public Client findById(@PathVariable Long id) throws ClientNotFoundException {
        if(clientService.findById(id).isEmpty())
            throw new ClientNotFoundException("Client with id "+id+" not found");
        else
            return clientService.findById(id).get();
    }*/

    //POST
    @PostMapping("/clients")
    public void createClient(@RequestBody Client client){
        clientService.createClient(client);
    }
}
