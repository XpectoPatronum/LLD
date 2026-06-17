package org.example.realSystems.bookMyShow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Show {
    private String showId;
    private Theatre theatre;
    private Screen screen;
    private Movie movie;
    private Date startTime;
    private Date endTime;

    public List<Seat> getShowSeats() {
        return screen.getSeats();
    }


}
