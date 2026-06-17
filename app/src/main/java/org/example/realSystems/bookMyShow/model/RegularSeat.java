package org.example.realSystems.bookMyShow.model;

import org.example.realSystems.bookMyShow.enums.SeatType;

public class RegularSeat extends Seat{
    public RegularSeat(String seatId, Double price) {
        super(seatId, price);
    }
    public SeatType getType(){
        return SeatType.REGULAR;
    }
}
