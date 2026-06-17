package org.example.realSystems.bookMyShow.repository;

import org.example.realSystems.bookMyShow.model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    private final Map<String, Movie> movies = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getMovieId(), movie);
    }

    public Movie getMovie(String movieId) {
        if(movies.containsKey(movieId)){
            return movies.get(movieId);
        }
        else{
            System.err.println("Movie with id " + movieId + " not found");
            return null;
        }
    }
}
