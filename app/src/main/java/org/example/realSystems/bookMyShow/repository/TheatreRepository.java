package org.example.realSystems.bookMyShow.repository;

import org.example.realSystems.bookMyShow.model.Screen;
import org.example.realSystems.bookMyShow.model.Seat;
import org.example.realSystems.bookMyShow.model.Theatre;

import java.util.HashMap;
import java.util.Map;

public class TheatreRepository {
    Map<String, Theatre> theatres = new HashMap<>();

    public void add(Theatre theatre) {
        theatres.put(theatre.getTheatreId(), theatre);
    }

    public Theatre getTheatre(String theatreId) {
        return theatres.get(theatreId);
    }

    public void addSeats(String theatreId, String screenId, Seat seat) {
        Theatre theatre = getTheatre(theatreId);
        Screen screen = theatre.getScreen(screenId);
        screen.addSeat(seat);
    }
}
