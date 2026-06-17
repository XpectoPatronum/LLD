package org.example.realSystems.bookMyShow.factory;

import org.example.realSystems.bookMyShow.enums.PaymentType;
import org.example.realSystems.bookMyShow.strategy.payment.CardPaymentStrategy;
import org.example.realSystems.bookMyShow.strategy.payment.PaymentStrategy;
import org.example.realSystems.bookMyShow.strategy.payment.UpiPaymentStrategy;

public class PaymentStrategyFactory {
    public static PaymentStrategy getPaymentStrategy(PaymentType paymentType) {
        return switch (paymentType) {
            case UPI -> new UpiPaymentStrategy();
            case CARD -> new CardPaymentStrategy();
            default -> throw new IllegalArgumentException("Invalid PaymentStrategy");
        };
    }
}
