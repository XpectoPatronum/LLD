package org.example.realSystems.bookMyShow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
public abstract class Seat {
    private String seatId;
    private Double price;
}
