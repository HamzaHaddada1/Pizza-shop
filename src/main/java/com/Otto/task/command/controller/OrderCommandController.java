package com.Otto.task.command.controller;

import com.Otto.task.command.commands.CancelOrderCommand;
import com.Otto.task.command.commands.CreateOrderCommand;
import com.Otto.task.command.commands.SendOrderToDeliveryCommand;
import com.Otto.task.lib.dtos.CreateOrderRequestDTO;
import com.Otto.task.lib.entity.PaymentMethod;
import com.Otto.task.lib.mapper.ItemDtoToItemMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ItemDtoToItemMapper mapper;


    @PostMapping
    public ResponseEntity createOrder(@Valid @RequestBody CreateOrderRequestDTO request) {
        try {
            String orderNumber = UUID.randomUUID().toString();
            commandGateway.send(
                    new CreateOrderCommand(
                            orderNumber,
                            PaymentMethod.toPaymentMethod(request.getPaymentMethod()),
                            mapper.ToItem(request.getItems()),
                            request.getPrice(),
                            request.getCustomerId(),
                            request.getAddress()
                    )
            );
            return new ResponseEntity(orderNumber, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cancel/{orderNumber}")
    public CompletableFuture<String> cancelOrder(@PathVariable String orderNumber){
        return commandGateway.send(
                new CancelOrderCommand(
                        orderNumber
                )
        );
    }

    @PutMapping("/private/deliver/{orderNumber}")
    public CompletableFuture<String> deliverOrder(@PathVariable String orderNumber){
        return commandGateway.send(
                new SendOrderToDeliveryCommand(
                        orderNumber
                )
        );
    }
}
