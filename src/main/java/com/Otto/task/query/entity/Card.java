package com.Otto.task.query.entity;


import com.Otto.task.lib.entity.PaymentMethod;
import com.Otto.task.lib.entity.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Card {
    @Id
    private String orderNumber;
    private String address;
    private double price;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private State state;
    private String customerId;
    private String items;

    public Card(String orderNumber, String address, double price, PaymentMethod paymentMethod, State state, String customerId,String items ) {
        this.orderNumber = orderNumber;
        this.address = address;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.state = state;
        this.customerId = customerId;
        this.items = items;
    }
}
