package com.teamtwo.backendtheater.service;

import com.teamtwo.backendtheater.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket saveTicket(Ticket ticket);

    void deleteTicket(Long id);

    List<Ticket> findAllTickets();
}
