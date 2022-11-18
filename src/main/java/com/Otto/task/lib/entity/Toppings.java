package com.Otto.task.lib.entity;

public enum Toppings {
    ONION(1),
    SHRIMP(2.5),
    OLIVES(1.5);

    public double price;

    Toppings(double price) {
        this.price = price;
    }

    public static Toppings toTopping(String toppings) {
        return switch (toppings.toLowerCase()) {
            case "olives" -> Toppings.OLIVES;
            case "shrimp" -> Toppings.SHRIMP;
            case "onion" -> Toppings.ONION;
            default -> throw new IllegalArgumentException("Topping is not in our menu");
        };
    }
}
