package org.example.realSystems.bookMyShow.repository;

import lombok.Getter;
import org.example.realSystems.bookMyShow.model.Booking;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BookingRepository {
    private final Map<String, Booking> bookings =  new HashMap<>();

    public void addBooking(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }
}
