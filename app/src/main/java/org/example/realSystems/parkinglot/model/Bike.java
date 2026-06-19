package org.example.realSystems.parkinglot.model;

import org.example.realSystems.parkinglot.enums.VehicleType;

public class Bike extends Vehicle {

    public Bike(String vehicleNumber, VehicleType vehicleType) {
        super(vehicleNumber, vehicleType);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.BIKE;
    }
}
