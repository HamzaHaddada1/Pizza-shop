package com.Otto.task.lib.events;

import lombok.Getter;

@Getter
public abstract class BaseEvent<T> {

    private final T id;
    public BaseEvent(T id) {
        this.id = id;
    }
}
