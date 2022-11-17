package com.Otto.task.lib.events;

import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.PaymentMethod;
import com.Otto.task.lib.entity.State;
import lombok.Getter;

import java.util.List;

@Getter
public final class OrderCreatedEvent extends BaseEvent<String> {

    private final PaymentMethod paymentMethod;
    private final String address;
    private final List<Item> items;
    private final double price;
    private final State state;
    private final String customerId;

    public OrderCreatedEvent(String id, PaymentMethod paymentMethod, String address, List<Item> items, double price, State state, String customerId) {
        super(id);
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.items = items;
        this.price = price;
        this.state = state;
        this.customerId = customerId;
    }
}