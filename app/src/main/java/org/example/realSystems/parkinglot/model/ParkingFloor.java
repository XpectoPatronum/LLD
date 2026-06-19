package org.example.realSystems.parkinglot.model;

import lombok.Getter;
import org.example.realSystems.parkinglot.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ParkingFloor {
    private final String id;
    Map<String, ParkingSpot> parkingSpots = new HashMap<>();

    public ParkingFloor(String id) {
        this.id = id;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpots.put(parkingSpot.getId(), parkingSpot);
    }

    public ParkingSpot getParkingSpot(String id) {
        return parkingSpots.get(id);
    }

    public ParkingSpot getParkingSpot(VehicleType  vehicleType) {
        for(ParkingSpot parkingSpot : parkingSpots.values()) {
            if(parkingSpot.getVehicleType().equals(vehicleType) && parkingSpot.tryOccupy()) {
                return parkingSpot;
            }
        }
        return null;
    }
}
