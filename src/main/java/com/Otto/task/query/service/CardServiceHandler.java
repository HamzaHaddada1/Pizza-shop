package com.Otto.task.query.service;

import com.Otto.task.lib.entity.State;
import com.Otto.task.lib.events.OrderCanceledEvent;
import com.Otto.task.lib.events.OrderCreatedEvent;
import com.Otto.task.lib.events.OrderSentToDeliveryEvent;
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

        orderRepository.save(new Card(
                orderCreatedEvent.getId(),
                orderCreatedEvent.getAddress(),
                orderCreatedEvent.getPrice(),
                orderCreatedEvent.getPaymentMethod(),
                State.CREATED,
                orderCreatedEvent.getCustomerId(),
                orderCreatedEvent.getItems().stream().map(Object::toString).reduce(" ", String::concat)
        ));
    }

    @EventHandler
    public void on(OrderCanceledEvent orderCanceledEvent) {
        log.info("OrderCanceledEvent has been triggered");

        Card card = orderRepository.findById(orderCanceledEvent.getId()).get();
        card.setState(State.CANCELED);
        orderRepository.save(card);
    }

    @EventHandler
    public void on(OrderSentToDeliveryEvent orderSentToDeliveryEvent) {
        log.info("OrderSentToDeliveryEvent has been triggered");

        Card card = orderRepository.findById(orderSentToDeliveryEvent.getId()).get();
        card.setState(State.DELIVERED);
        orderRepository.save(card);
    }
}
