package com.Otto.task.command.aggregate;

import com.Otto.task.command.commands.CancelOrderCommand;
import com.Otto.task.command.commands.CreateOrderCommand;
import com.Otto.task.command.commands.SendOrderToDeliveryCommand;
import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.PaymentMethod;
import com.Otto.task.lib.entity.State;
import com.Otto.task.lib.events.OrderCanceledEvent;
import com.Otto.task.lib.events.OrderCreatedEvent;
import com.Otto.task.lib.events.OrderSentToDeliveryEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
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
                calculatePrice(createOrderCommand.getItems()),
                State.CREATED,
                createOrderCommand.getCustomerId()
        ));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        this.orderNumber = orderCreatedEvent.getId();
        this.paymentMethod = orderCreatedEvent.getPaymentMethod();
        this.price = calculatePrice(orderCreatedEvent.getItems());
        this.items = orderCreatedEvent.getItems();
        this.state = State.CREATED;
        this.customerId = orderCreatedEvent.getCustomerId();
        this.timestamp = LocalDateTime.now();
    }

    @CommandHandler
    public void handle(CancelOrderCommand cancelOrderCommand) {
        if (this.state.equals(State.DELIVERED)) {
             throw new RuntimeException("Cannot Cancel while in delivery");
        }
        AggregateLifecycle.apply(new OrderCanceledEvent(
                cancelOrderCommand.getId()
        ));
    }

    @EventSourcingHandler
    public void on(OrderCanceledEvent orderCanceledEvent) {
        this.state = State.CANCELED;
        this.timestamp = LocalDateTime.now();
    }


    @CommandHandler
    public void handle(SendOrderToDeliveryCommand sendOrderToDeliveryCommand) {
        if (this.state.equals(State.CANCELED)) {
            throw new RuntimeException("Cannot sent a canceled order to delivery");
        }
        AggregateLifecycle.apply(new OrderSentToDeliveryEvent(
                sendOrderToDeliveryCommand.getId()
        ));
    }

    @EventSourcingHandler
    public void on(OrderSentToDeliveryEvent orderSentToDeliveryEvent) {
        this.state = State.DELIVERED;
        this.timestamp = LocalDateTime.now();
    }

    private double calculatePrice(List<Item> items){
        double pizzaPrice = items
                .stream()
                .map(item -> item.getPizza().price)
                .reduce(0.0,Double::sum);
        double toppingsPrice = items.stream()
                .map(item -> item.getToppings()
                        .stream()
                        .map(topping -> topping.price)
                        .reduce(0.0,Double::sum)
                ).findFirst().get();
        return pizzaPrice + toppingsPrice;
    }
}
