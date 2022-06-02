package net.clotfelter.duncan.ShoppingCartDemo.entities.products;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tickets")
@Data
public class Ticket {
    @Id @GeneratedValue @Column(name="id")
    int id;

    @Column(name="moviedb_id")
    String moviedbId;

    @Column(name="show_time")
    String showTime;

    @Column(name="units")
    int units;

    @Deprecated
    @Column(name="user")
    String user;

    @Deprecated
    @Column(name="film")
    String film;

    @Deprecated
    @Column(columnDefinition = "TEXT")
    String purchase;

    public int getPrice() {return 10;}//TODO dynamic pricing, currently $10/ticket
}