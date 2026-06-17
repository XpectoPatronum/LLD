package org.example.realSystems.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.example.realSystems.bookMyShow.model.Seat;
import org.example.realSystems.bookMyShow.model.Theatre;
import org.example.realSystems.bookMyShow.repository.TheatreRepository;

@AllArgsConstructor
public class TheatreService {
    private final TheatreRepository theatreRepository;

    public Theatre getTheatre(String theatreId) {
        return theatreRepository.getTheatre(theatreId);
    }

    public Theatre addTheatre(String theatreId, String theatreName) {
        Theatre theatre = new Theatre(theatreId,theatreName);
        theatreRepository.add(theatre);
        return theatre;
    }

    public void addSeats(String theatreId, String screenId, Seat seat) {
        theatreRepository.addSeats(theatreId,screenId,seat);
    }
}
