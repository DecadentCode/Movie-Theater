package com.teamtwo.backendtheater.controller;

import com.teamtwo.backendtheater.model.Ticket;
import com.teamtwo.backendtheater.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    ///////////////////////////////////////////////////////////
    // due to oAuth2, log-in && register links have been CUT //
    ///////////////////////////////////////////////////////////

    @Autowired
    private TicketService ticketService;

    ///////////////////////////////////////////////////////////

    @GetMapping("/") // just here for testing
    public String landing() {
        return "Hello world.";
    }

    @GetMapping("/ticket")
    public List<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }

}
