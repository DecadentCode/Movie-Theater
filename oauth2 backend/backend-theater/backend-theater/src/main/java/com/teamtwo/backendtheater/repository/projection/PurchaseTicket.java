package com.teamtwo.backendtheater.repository.projection;

import javax.persistence.criteria.CriteriaBuilder;

public interface PurchaseTicket {
    String getName();
    Double getPrice();
    Integer getShowTime();
}
