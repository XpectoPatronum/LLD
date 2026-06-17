package org.example.realSystems.bookMyShow.strategy.payment;

import org.example.realSystems.bookMyShow.enums.BookingStatus;
import org.example.realSystems.bookMyShow.model.Booking;

public class UpiPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        System.out.println(booking.getUserId() + " paid " + booking.getAmount() + " using UPI\n");
        return true;
    }
}
