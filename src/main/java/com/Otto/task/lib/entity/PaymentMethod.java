package com.Otto.task.lib.entity;

public enum PaymentMethod {
    ONLINE,
    OFFLINE;

    public static PaymentMethod toPaymentMethod(String paymentMethod){
        switch (paymentMethod.toLowerCase()){
            case "online": return PaymentMethod.ONLINE;
            case "offline": return  PaymentMethod.OFFLINE;
            default: throw new IllegalArgumentException("Payment method is not supported");
        }
    }
}
