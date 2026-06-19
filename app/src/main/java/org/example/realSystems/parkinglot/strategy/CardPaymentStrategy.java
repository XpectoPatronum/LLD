package org.example.realSystems.parkinglot.strategy;

public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(Double amount) {
        System.out.println(amount + " paid by upi");
        return true;
    }
}
