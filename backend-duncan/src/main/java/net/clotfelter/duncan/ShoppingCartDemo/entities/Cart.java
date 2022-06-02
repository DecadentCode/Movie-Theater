package net.clotfelter.duncan.ShoppingCartDemo.entities;

import lombok.Data;
import lombok.ToString;
import net.clotfelter.duncan.ShoppingCartDemo.entities.products.Ticket;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name="purchases")
public class Cart {
    @ManyToMany @Fetch(FetchMode.JOIN)
    private Set<Ticket> tickets = new HashSet<>();

    @Column(name="user_id")
    String userId;

    @Id
    @Column(name="payment_id")
    String paymentId;

    @Column(columnDefinition = "TEXT", name="payment_details")
    String paymentDetails;

    @ToString.Include
    public double getTotal() {
        return tickets.stream().mapToDouble(p -> (p.getPrice() * p.getUnits())).reduce(0, Double::sum);
    }
}