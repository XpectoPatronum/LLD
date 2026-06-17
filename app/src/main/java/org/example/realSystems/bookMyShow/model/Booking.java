package org.example.realSystems.bookMyShow.model;

import com.google.j2objc.annotations.Weak;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.realSystems.bookMyShow.enums.BookingStatus;
import org.example.realSystems.bookMyShow.enums.PaymentType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Booking {
    private final String bookingId;
    private final String userId;
    private String showId;
    private final List<String> seats;
    private BookingStatus bookingStatus;
    private PaymentType paymentType;
    private double amount;

    public Booking(String bookingId, String userId, List<String> seats) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", userId='" + userId + '\'' +
                ", showId='" + showId + '\'' +
                ", seats=" + seats +
                ", bookingStatus=" + bookingStatus +
                ", paymentType=" + paymentType +
                ", amount=" + amount +
                '}';
    }
}
