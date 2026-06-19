package org.example.realSystems.parkinglot.strategy;

import org.example.realSystems.parkinglot.model.Ticket;
import org.example.realSystems.parkinglot.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class EventBasedPricingStrategy implements PricingStrategy {
    private static final Map<VehicleType,Double> pricePerEvent = new HashMap<>();

    public EventBasedPricingStrategy() {
        super();
    }

    static {
        pricePerEvent.put(VehicleType.BIKE,80D);
        pricePerEvent.put(VehicleType.CAR,150D);
        pricePerEvent.put(VehicleType.BUS,500D);
        pricePerEvent.put(VehicleType.TRUCK,800D);
    }

    @Override
    public double calculateFees(Ticket ticket) {
        return pricePerEvent.get(ticket.getVehicle().getVehicleType());
    }
}
