package com.example.backendtheater.product;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ticket")
@Data
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @Column(name="id")
    int id = 0;

    @Column(name="price")
    double price;

    @Column(name="currency")
    String currency;

    @Column(name="units")
    int units;

    @Column(name="production_name")
    String productionName;

    @Column(name="show_time")
    LocalDateTime showTime;
}
