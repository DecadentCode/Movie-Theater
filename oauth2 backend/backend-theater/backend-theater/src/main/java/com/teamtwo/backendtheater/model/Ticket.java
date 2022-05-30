package com.teamtwo.backendtheater.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "show_time", nullable = false)
    private Integer showTime;

}
