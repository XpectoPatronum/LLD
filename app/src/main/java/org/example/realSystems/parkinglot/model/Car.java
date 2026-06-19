package org.example.realSystems.parkinglot.model;

import org.example.realSystems.parkinglot.enums.VehicleType;

public class Car extends Vehicle {

    public Car(String vehicleNumber, VehicleType vehicleType) {
        super(vehicleNumber, vehicleType);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}
