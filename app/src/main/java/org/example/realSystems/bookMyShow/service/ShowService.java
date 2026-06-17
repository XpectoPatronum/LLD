package org.example.realSystems.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.example.realSystems.bookMyShow.model.Movie;
import org.example.realSystems.bookMyShow.model.Screen;
import org.example.realSystems.bookMyShow.model.Show;
import org.example.realSystems.bookMyShow.model.Theatre;
import org.example.realSystems.bookMyShow.repository.ShowRepository;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    public Show createShow(String showId, Theatre theatre, Screen screen, Movie movie, Date startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE, movie.getDurationInMinutes());
        Show show = new Show(showId, theatre, screen, movie, startTime, calendar.getTime());
        showRepository.addShows(show);
        return show;
    }

    public Show getShow(String showId) {
        return showRepository.getShow(showId);
    }

    public List<Show> getShows(String movieName) {
        return showRepository.getAllShows().stream().filter(show -> show.getMovie().getTitle().equalsIgnoreCase(movieName)).toList();
    }
}
