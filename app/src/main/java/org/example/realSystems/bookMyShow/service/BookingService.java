package org.example.realSystems.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.example.realSystems.bookMyShow.enums.BookingStatus;
import org.example.realSystems.bookMyShow.enums.PaymentType;
import org.example.realSystems.bookMyShow.factory.PaymentStrategyFactory;
import org.example.realSystems.bookMyShow.model.Booking;
import org.example.realSystems.bookMyShow.model.Seat;
import org.example.realSystems.bookMyShow.model.Show;
import org.example.realSystems.bookMyShow.repository.BookingRepository;
import org.example.realSystems.bookMyShow.strategy.locking.LockProvider;
import org.example.realSystems.bookMyShow.strategy.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final LockProvider lockProvider;

    private final long TTL = 5000L;
    public Booking createBooking(String userId, Show show, List<String> seats) {
        List<String> sortedSeats = new ArrayList<>(seats);
        Collections.sort(sortedSeats);
        List<String> keysOfSuccessfulLocks = new ArrayList<>();
        try {
            for (String seatId : sortedSeats) {
                String key = show.getShowId() + ":" + seatId;
                if (!lockProvider.tryLock(key, userId, TTL)) {
                    System.out.println("Unable to acquire lock for booking seat " + seatId + " for user " + userId);
                    throw new RuntimeException("Seat " + seatId + " not available temporarily");
                }else{
                    keysOfSuccessfulLocks.add(key);
                }
            }
        } catch (Exception e) {
//            System.out.println("Unlocking the locked keys due to failed");
            keysOfSuccessfulLocks.forEach(lockProvider::unlock);
            throw new RuntimeException(e.getMessage());
        }

        double totalPrice = 0.0d;
        for(Seat seat : show.getShowSeats()){
            if(sortedSeats.contains(seat.getSeatId())){
                totalPrice += seat.getPrice();
            }
        }

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                userId,
                show.getShowId(),
                seats,
                BookingStatus.CREATED,
                null,
                totalPrice
        );

        bookingRepository.addBooking(booking);
        System.out.println("Booking " + booking.getBookingId() + " created");
        return booking;
    }

    public void confirmBooking(String bookingId, PaymentType paymentType) {
        Booking booking = bookingRepository.getBooking(bookingId);
        if(!BookingStatus.CREATED.equals(booking.getBookingStatus())){
            throw new IllegalStateException("Booking is not in a valid state");
        }
        String keyPref = booking.getShowId() + ":";
        for(String seatId : booking.getSeats()){
            String key = keyPref + seatId;
            if(lockProvider.isLockExpired(key) || !lockProvider.isLockedBy(key,booking.getUserId())){
                throw new RuntimeException("Seat " + seatId + " not available temporarily");
            }
        }

        booking.setPaymentType(paymentType);
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(paymentType);
        booking.setBookingStatus(BookingStatus.COMPLETED);
        paymentStrategy.pay(booking);
        System.out.println("Booking " + booking.getBookingId() + " confirmed");
    }
}
