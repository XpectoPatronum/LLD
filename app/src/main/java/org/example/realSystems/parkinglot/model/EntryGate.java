package org.example.realSystems.parkinglot.model;

import org.example.realSystems.parkinglot.enums.GateType;
import org.example.realSystems.parkinglot.enums.VehicleType;
import org.example.realSystems.parkinglot.service.ParkingLot;

import java.util.Date;

public class EntryGate extends Gate {

    public EntryGate(String gateId) {
        super(gateId, GateType.ENTRY_GATE);
    }

    @Override
    public GateType getGateType() {
        return GateType.ENTRY_GATE;
    }

    public Ticket parkVehicle(Vehicle vehicle,  Date entryTime) {
        return ParkingLot.getParkingLotInstance().parkVehicle(vehicle, entryTime);
    }

}
