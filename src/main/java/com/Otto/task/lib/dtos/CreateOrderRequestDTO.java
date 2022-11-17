package com.Otto.task.lib.dtos;


import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderRequestDTO {

    @NotNull(message = "Delivery address could not be null")
    private final String address;

    private final PaymentMethod paymentMethod;

    @NotNull(message = "Cannot initialize order without items")
    private  final List<Item> items;

    private final double price;

    @NotNull(message = "Customer Id is missing")
    private final String customerId;
}