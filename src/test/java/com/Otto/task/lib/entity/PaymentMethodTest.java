package com.Otto.task.lib.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {

    @Test
    void testToPaymentMethodSuccess() {
        assertEquals(PaymentMethod.OFFLINE, PaymentMethod.toPaymentMethod("offline"));
        assertEquals(PaymentMethod.OFFLINE, PaymentMethod.toPaymentMethod("OFFLINE"));

        assertEquals(PaymentMethod.ONLINE, PaymentMethod.toPaymentMethod("online"));
        assertEquals(PaymentMethod.ONLINE, PaymentMethod.toPaymentMethod("ONLINE"));
    }

    @Test
    void testToPaymentMethodFailure() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->PaymentMethod.toPaymentMethod("card"),
                "Payment method is not supported"
        );

        assertEquals(exception.getClass(),IllegalArgumentException.class);
        assertEquals(exception.getMessage(),"Payment method is not supported");

    }
}