package com.Otto.task.lib.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PizzaTest {

    @Test
    void testToPizzaSuccess() {
        assertEquals(Pizza.ROMA, Pizza.toPizza("roma"));
        assertEquals(Pizza.ROMA, Pizza.toPizza("ROMA"));

        assertEquals(Pizza.MARGHERITA, Pizza.toPizza("MARGHERITA"));
        assertEquals(Pizza.MARGHERITA, Pizza.toPizza("margherita"));

        assertEquals(Pizza.NAPOLI, Pizza.toPizza("NAPOLI"));
        assertEquals(Pizza.NAPOLI, Pizza.toPizza("napoli"));

        assertEquals(Pizza.NAPOLI.price, 12);
        assertEquals(Pizza.MARGHERITA.price, 8);
        assertEquals(Pizza.ROMA.price, 10);
    }

    @Test
    void testToPizzaFailure() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->Pizza.toPizza("randomPizza"),
                "Pizza is not in our menu"
        );

        assertEquals(exception.getClass(),IllegalArgumentException.class);
        assertEquals(exception.getMessage(),"Pizza is not in our menu");
    }
}