package com.Otto.task.lib.entity;

public enum Pizza {

    MARGHERITA( 8),
    ROMA(10),
    NAPOLI(12);


    public double price;

    Pizza(double price) {
        this.price = price;
    }

}
