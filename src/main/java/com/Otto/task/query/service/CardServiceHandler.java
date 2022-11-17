package com.Otto.task.query.service;

import com.Otto.task.lib.entity.State;
import com.Otto.task.lib.events.OrderCreatedEvent;
import com.Otto.task.query.entity.Card;
import com.Otto.task.query.repository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CardServiceHandler {

    private CardRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("OrderCreatedEvent has been triggered");

        String items = orderCreatedEvent.getItems() != null ? orderCreatedEvent.getItems().stream().map(Object::toString).reduce(" ", String::concat) : null;

        if (orderCreatedEvent.getItems() != null) orderCreatedEvent.getItems().forEach(System.out::println) ;
        orderRepository.save(new Card(
                orderCreatedEvent.getId(),
                orderCreatedEvent.getAddress(),
                orderCreatedEvent.getPrice(),
                orderCreatedEvent.getPaymentMethod(),
                State.CREATED,
                orderCreatedEvent.getCustomerId(),
                items
        ));
    }
}
