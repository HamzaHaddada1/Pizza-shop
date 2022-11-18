package com.Otto.task.lib.entity;

public enum PaymentMethod {
    ONLINE,
    OFFLINE;

    public static PaymentMethod toPaymentMethod(String paymentMethod){
        return switch (paymentMethod.toLowerCase()) {
            case "online" -> PaymentMethod.ONLINE;
            case "offline" -> PaymentMethod.OFFLINE;
            default -> throw new IllegalArgumentException("Payment method is not supported");
        };
    }
}
