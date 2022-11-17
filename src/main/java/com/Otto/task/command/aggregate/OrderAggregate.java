package com.Otto.task.command.aggregate;

import com.Otto.task.command.commands.CreateOrderCommand;
import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.PaymentMethod;
import com.Otto.task.lib.entity.State;
import com.Otto.task.lib.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.List;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderNumber;
    private String address;
    private List<Item> items;
    private double price;
    private PaymentMethod paymentMethod;
    private State state;
    private String customerId;
    private LocalDateTime timestamp;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        AggregateLifecycle.apply(new OrderCreatedEvent(
                createOrderCommand.getId(),
                createOrderCommand.getPaymentMethod(),
                createOrderCommand.getAddress(),
                createOrderCommand.getItems(),
                createOrderCommand.getPrice(),
                State.CREATED,
                createOrderCommand.getCustomerId()
        ));
    }
}
