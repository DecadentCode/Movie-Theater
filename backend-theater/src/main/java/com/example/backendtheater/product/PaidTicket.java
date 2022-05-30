package com.example.backendtheater.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PaidTicket extends Ticket {
    String payment;
}
