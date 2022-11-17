package com.Otto.task.lib.dtos;


import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderRequestDTO {

    private final String address;
    private final PaymentMethod paymentMethod;
    private  final List<Item> items;
    private final double price;
    private final String customerId;
}