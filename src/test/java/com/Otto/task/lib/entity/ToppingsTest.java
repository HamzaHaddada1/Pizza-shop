package com.Otto.task.lib.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToppingsTest {

    @Test
    void testToToppingSuccess() {

        assertEquals(Toppings.OLIVES, Toppings.toTopping("olives"));
        assertEquals(Toppings.OLIVES, Toppings.toTopping("OLIVES"));

        assertEquals(Toppings.ONION, Toppings.toTopping("ONION"));
        assertEquals(Toppings.ONION, Toppings.toTopping("ONION"));

        assertEquals(Toppings.OLIVES.price, 1.5);
        assertEquals(Toppings.ONION.price, 1);
    }

    @Test
    void testToToppingFailure() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->Toppings.toTopping("Shrimp"),
                "Topping is not in our menu"
        );

        assertEquals(exception.getClass(),IllegalArgumentException.class);
        assertEquals(exception.getMessage(),"Topping is not in our menu");
    }
}