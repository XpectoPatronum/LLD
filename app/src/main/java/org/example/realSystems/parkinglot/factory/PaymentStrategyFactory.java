package org.example.realSystems.parkinglot.factory;

import org.example.realSystems.parkinglot.enums.PaymentType;
import org.example.realSystems.parkinglot.strategy.CardPaymentStrategy;
import org.example.realSystems.parkinglot.strategy.PaymentStrategy;
import org.example.realSystems.parkinglot.strategy.UpiPaymentStrategy;

public class PaymentStrategyFactory {
    public static PaymentStrategy getPaymentStrategy(PaymentType paymentType) {
        if(paymentType == PaymentType.CARD) {
            return new CardPaymentStrategy();
        }else if(paymentType == PaymentType.UPI) {
            return new UpiPaymentStrategy();
        }else{
            throw new IllegalArgumentException("Unknown payment type");
        }
    }
}
