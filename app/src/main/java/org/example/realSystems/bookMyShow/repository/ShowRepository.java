package org.example.realSystems.bookMyShow.repository;

import lombok.Getter;
import org.example.realSystems.bookMyShow.model.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class ShowRepository {
    private final Map<String, Show> shows = new HashMap<>();

    public void addShows(Show show) {
        shows.put(show.getShowId(), show);
    }

    public Show getShow(String showId) {
        if (shows.containsKey(showId)) {
            return shows.get(showId);
        }
        else{
            System.err.println("No such show with id: " + showId);
            return null;
        }
    }

    public List<Show> getAllShows() {
        return new ArrayList<>(shows.values());
    }
}
