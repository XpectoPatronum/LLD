package org.example.realSystems.parkinglot.strategy;

import org.example.realSystems.parkinglot.enums.Payment_Status;
import org.example.realSystems.parkinglot.model.Ticket;
import org.example.realSystems.parkinglot.enums.VehicleType;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeBasedPricingStrategy implements PricingStrategy {
    private static final Map<VehicleType,Double> pricePerMinute = new HashMap<>();

    static {
        pricePerMinute.put(VehicleType.BIKE,(double)(10/60));
        pricePerMinute.put(VehicleType.CAR,(double)(20/60));
        pricePerMinute.put(VehicleType.BUS,(double)(40/60));
        pricePerMinute.put(VehicleType.TRUCK,(double)(50/60));
    }
    public TimeBasedPricingStrategy() {
        super();
    }
    @Override
    public double calculateFees(Ticket ticket) {
        VehicleType vehicleType = ticket.getVehicle().getVehicleType();
        Date entryTime = ticket.getEntryTime();
        Date exitTime = ticket.getExitTime();

        long timePassed = Duration.between(entryTime.toInstant(), exitTime.toInstant()).toMinutes();
        return pricePerMinute.get(vehicleType)*timePassed;
    }
}
