package org.example.realSystems.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.example.realSystems.parkinglot.enums.VehicleType;

@Getter
@AllArgsConstructor
public abstract class Vehicle {

    private String vehicleNumber;
    private VehicleType vehicleType;

    public abstract VehicleType getVehicleType();

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
