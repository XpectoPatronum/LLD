package org.example.realSystems.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.example.realSystems.bookMyShow.model.Movie;
import org.example.realSystems.bookMyShow.repository.MovieRepository;

@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie getMovies(String movieId) {
        return movieRepository.getMovie(movieId);
    }

    public Movie createMovie(String id, String title, int durationInMinutes) {
        Movie movie = new Movie(id,title,durationInMinutes);
        movieRepository.addMovie(movie);
        return movie;
    }

}
