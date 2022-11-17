package com.Otto.task.command.commands;


import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.PaymentMethod;
import lombok.Getter;

import java.util.List;

@Getter
public final class CreateOrderCommand extends BaseCommand<String> {

    private final PaymentMethod paymentMethod;
    private final List<Item> items;
    private final double price;
    private final String customerId;
    private final String address;

    public CreateOrderCommand(String id, PaymentMethod paymentMethod, List<Item> items, double price, String customerId, String address) {
        super(id);
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.price = price;
        this.customerId = customerId;
        this.address = address;
    }

}
