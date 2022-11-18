package com.Otto.task.command.controller;

import com.Otto.task.command.commands.CancelOrderCommand;
import com.Otto.task.command.commands.CreateOrderCommand;
import com.Otto.task.lib.dtos.CreateOrderRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/v1/order")
@AllArgsConstructor
@Slf4j
public class OrderCommandController {

    @Autowired
    CommandGateway commandGateway;


    @PostMapping
    public CompletableFuture<String> createOrder(@Valid @RequestBody CreateOrderRequestDTO request) {

        return commandGateway.send(
                new CreateOrderCommand(
                        UUID.randomUUID().toString(),
                        request.getPaymentMethod(),
                        request.getItems(),
                        request.getPrice(),
                        request.getCustomerId(),
                        request.getAddress()
                )
        );
    }

    @PutMapping("/cancel/{orderNumber}")
    public CompletableFuture<String> cancelOrder(@PathVariable String orderNumber){
        return commandGateway.send(
                new CancelOrderCommand(
                        orderNumber
                )
        );
    }
}
