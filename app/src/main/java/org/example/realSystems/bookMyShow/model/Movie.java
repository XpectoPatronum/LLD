package org.example.realSystems.bookMyShow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Movie {
    private String movieId;
    private String title;
    private int durationInMinutes;
}
