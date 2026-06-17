package org.example.realSystems.bookMyShow.strategy.payment;

import org.example.realSystems.bookMyShow.model.Booking;

public interface PaymentStrategy {
    boolean pay(Booking booking);
}
