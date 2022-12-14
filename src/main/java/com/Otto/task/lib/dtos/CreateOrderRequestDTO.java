package com.Otto.task.lib.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderRequestDTO {

    @NotEmpty
    @NotNull(message = "Delivery address could not be null")
    private final String address;

    private final String paymentMethod;

    @NotEmpty
    @NotNull(message = "Cannot initialize order without items")
    @Valid
    private final List<ItemDTO> items;

    private final double price;

    @NotEmpty
    @NotNull(message = "Customer Id is missing")
    private final String customerId;
}