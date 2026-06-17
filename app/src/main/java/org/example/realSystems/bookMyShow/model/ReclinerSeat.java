package org.example.realSystems.bookMyShow.model;

import org.example.realSystems.bookMyShow.enums.SeatType;

public class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatId, Double price) {
        super(seatId, price);
    }
    public SeatType getSeatType() {
        return SeatType.RECLINER;
    }
}
