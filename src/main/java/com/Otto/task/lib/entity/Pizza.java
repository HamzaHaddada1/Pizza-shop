package com.Otto.task.lib.entity;

public enum Pizza {

    MARGHERITA( 8),
    ROMA(10),
    NAPOLI(12);


    public double price;

    Pizza(double price) {
        this.price = price;
    }

    public static Pizza toPizza(String pizza) {
        return switch (pizza.toLowerCase()) {
            case "margherita" -> Pizza.MARGHERITA;
            case "roma" -> Pizza.ROMA;
            case "napoli" -> Pizza.NAPOLI;
            default -> throw new IllegalArgumentException("Pizza is not in our menu");
        };
    }
}
