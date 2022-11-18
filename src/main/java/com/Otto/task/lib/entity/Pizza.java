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
        switch (pizza.toLowerCase()) {
            case "margherita": return Pizza.MARGHERITA;
            case "roma": return Pizza.ROMA;
            case "napoli": return Pizza.NAPOLI;
            default: throw new IllegalArgumentException("Pizza is not in our menu");
        }
    }
}
