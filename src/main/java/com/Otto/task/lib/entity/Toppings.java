package com.Otto.task.lib.entity;

public enum Toppings {
    ONION(1),
    SHRIMP(2.5),
    OLIVES(1.5);

    public double price;

    Toppings(double price) {
        this.price = price;
    }
}
