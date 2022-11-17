package com.Otto.task.lib.events;

public class OrderCanceledEvent extends BaseEvent<String> {

    public OrderCanceledEvent(String id) {
        super(id);
    }
}

