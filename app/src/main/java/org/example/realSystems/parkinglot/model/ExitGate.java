package org.example.realSystems.parkinglot.model;

import org.example.realSystems.parkinglot.enums.GateType;
import org.example.realSystems.parkinglot.enums.PaymentType;
import org.example.realSystems.parkinglot.service.ParkingLot;

import java.util.Date;

public class ExitGate extends Gate {

    public ExitGate(String gateId) {
        super(gateId, GateType.EXIT_GATE);
    }

    @Override
    public GateType getGateType() {
        return GateType.EXIT_GATE;
    }

    public void unPark(Ticket ticket, Date exitTime, PaymentType paymentType) {
        ParkingLot.getParkingLotInstance().unParkVehicle(ticket, exitTime, paymentType);
    }
}
