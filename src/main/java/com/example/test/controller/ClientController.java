package com.example.test.controller;

import com.example.test.model.ClientDTO;
import com.example.test.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/client")
    public ClientDTO getClientById(@RequestParam Long id) {
        return clientService.getClientById(id);
    }
}
