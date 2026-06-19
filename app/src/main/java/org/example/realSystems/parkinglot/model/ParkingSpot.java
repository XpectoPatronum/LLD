package org.example.realSystems.parkinglot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.realSystems.parkinglot.enums.VehicleType;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@RequiredArgsConstructor
public class ParkingSpot {
    private final String id;
    private final VehicleType vehicleType;

    private AtomicBoolean available = new AtomicBoolean(true);


    public boolean tryOccupy(){
        return available.compareAndSet(true,false);
    }
    public void vacate(){
        available.set(true);
    }
    public boolean isAvailable(){
        return available.get();
    }

}
