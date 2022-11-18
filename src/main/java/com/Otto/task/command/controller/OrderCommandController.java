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
            commandGateway.sendAndWait(
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
    public ResponseEntity cancelOrder(@PathVariable String orderNumber){
        try {
             commandGateway.sendAndWait(
                    new CancelOrderCommand(
                            orderNumber
                    )
            );
            return new ResponseEntity(orderNumber, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/private/deliver/{orderNumber}")
    public ResponseEntity deliverOrder(@PathVariable String orderNumber){
        try {
            commandGateway.sendAndWait(
                    new SendOrderToDeliveryCommand(
                            orderNumber
                    )
            );
            return new ResponseEntity(orderNumber, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
