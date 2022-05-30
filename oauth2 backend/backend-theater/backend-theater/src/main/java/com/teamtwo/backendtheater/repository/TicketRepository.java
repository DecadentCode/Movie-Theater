package com.teamtwo.backendtheater.repository;

import com.teamtwo.backendtheater.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository <Ticket, Long> {
}
