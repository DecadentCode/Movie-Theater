package com.teamtwo.backendtheater.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "purchase")
public class Purchase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    ///////////////////////////////////

    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Ticket ticket;

    ///////////////////////////////////

    @Column(name = "contact_email", nullable = false)
    private String email;

    @Column(name = "purchase_time", nullable = false)
    private LocalDateTime purchaseTime;

}
