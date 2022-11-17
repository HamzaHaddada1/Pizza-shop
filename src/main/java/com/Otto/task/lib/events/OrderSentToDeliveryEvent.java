package com.Otto.task.lib.events;

public class OrderSentToDeliveryEvent extends BaseEvent<String>{
    public OrderSentToDeliveryEvent(String id) {
        super(id);
    }
}
