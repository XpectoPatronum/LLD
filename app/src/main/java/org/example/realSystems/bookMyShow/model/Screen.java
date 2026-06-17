package org.example.realSystems.bookMyShow.model;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class Screen {
    private final String screenId;
    private final Map<String,Seat> seats = new HashMap<>();

    public Screen(String screenId) {
        this.screenId = screenId;
    }

    public void addSeat(Seat seat) {
        seats.put(seat.getSeatId(), seat);
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats.values());
    }
}
