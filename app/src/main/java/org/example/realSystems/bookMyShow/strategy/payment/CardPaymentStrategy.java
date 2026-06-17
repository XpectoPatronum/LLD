package org.example.realSystems.bookMyShow.strategy.payment;

import org.example.realSystems.bookMyShow.enums.BookingStatus;
import org.example.realSystems.bookMyShow.model.Booking;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        System.out.println(booking.getUserId() + " paid " + booking.getAmount() + " using CARD\n");
        return true;
    }
}
