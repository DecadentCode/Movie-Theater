package com.teamtwo.backendtheater.controller;

import com.teamtwo.backendtheater.model.Ticket;
import com.teamtwo.backendtheater.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostmanController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/edit")
    public void saveTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
        System.out.println("success");
    }
}
