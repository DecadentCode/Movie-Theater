package com.teamtwo.backendtheater.repository;

import com.teamtwo.backendtheater.model.Purchase;
import com.teamtwo.backendtheater.repository.projection.PurchaseTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository <Purchase, Long> {

    @Query("select " +
            "tic.name as name, tic.price as price, tic.showTime as showTime " +
            "from Purchase pur left join Ticket tic on tic.id = pur.ticketId " +
            "where pur.email = :email")
    List<PurchaseTicket> findAllPurchasesOfUser(@Param("email") String email);

}
