package org.example.realSystems.parkinglot.service;

import lombok.Getter;
import org.example.realSystems.parkinglot.enums.PaymentType;
import org.example.realSystems.parkinglot.enums.Payment_Status;
import org.example.realSystems.parkinglot.enums.Pricing_Strategy;
import org.example.realSystems.parkinglot.factory.PaymentStrategyFactory;
import org.example.realSystems.parkinglot.factory.PricingStrategyFactory;
import org.example.realSystems.parkinglot.model.ParkingFloor;
import org.example.realSystems.parkinglot.model.ParkingSpot;
import org.example.realSystems.parkinglot.model.Ticket;
import org.example.realSystems.parkinglot.model.Vehicle;
import org.example.realSystems.parkinglot.strategy.PricingStrategy;

import java.util.*;

@Getter
public class ParkingLot {
    // ********** 1st method for Singleton class instance creation **********
//
//    static volatile ParkingLot parkingLotInstance;
//
//    static ParkingLot getParkingLotInstance() {
//        //double locking
//        if(parkingLotInstance == null) {
//            synchronized (ParkingLot.class) {
//                if(parkingLotInstance == null) {
//                    parkingLotInstance = new ParkingLot();
//                }
//            }
//        }
//        return parkingLotInstance;
//    }


    // ********** 2nd method for Singleton class instance creation **********

    private ParkingLot() {
        pricingStrategy = PricingStrategyFactory.getInstance(Pricing_Strategy.EVENT_BASED);
    }

    private static class ParkingLotHelper {
        private static final ParkingLot INSTANCE = new ParkingLot();
    }
    public static ParkingLot getParkingLotInstance() {
        return ParkingLotHelper.INSTANCE;
    }

    PricingStrategy pricingStrategy;
    Map<String, ParkingFloor> parkingFloors = new HashMap<>();
    Map<String, Ticket> activeTickets = new HashMap<>();

    public ParkingFloor getParkingFloor(String parkingFloorId) {
        return parkingFloors.get(parkingFloorId);
    }

    public List<ParkingFloor> getAllParkingFloors() {
        return new ArrayList<>(parkingFloors.values());
    }

    public void addParkingFloor(ParkingFloor floor) {
        parkingFloors.put(floor.getId(),  floor);
    }

    public Ticket parkVehicle(Vehicle vehicle, Date entryTime) {
        Ticket ticket = new Ticket(
                String.valueOf(UUID.randomUUID()),
                vehicle,
                null,
                Payment_Status.PENDING,
                entryTime,
                null,
                null,
                null
        );
        activeTickets.put(ticket.getTicketId(),ticket);
        boolean alloted = false;
        for(ParkingFloor parkingFloor : getAllParkingFloors()){
            ParkingSpot spotToPark = parkingFloor.getParkingSpot(vehicle.getVehicleType());
            if(spotToPark != null){
                ticket.setParkingFloorId(parkingFloor.getId());
                ticket.setParkingSlot(spotToPark.getId());
                alloted = true;
            }
        }
        if(!alloted){
            System.out.println("No parking slot available for " + vehicle.getVehicleType());
            return null;
        }
        System.out.println("Parking slot available for " + vehicle.getVehicleType());
        System.out.println("Ticket generated with ticket id " + ticket.getTicketId());
        System.out.println(ticket);
        return ticket;
    }

    public void unParkVehicle(Ticket ticket, Date exitTime, PaymentType paymentType) {
        if(activeTickets.containsKey(ticket.getTicketId())) {
            activeTickets.remove(ticket.getTicketId());
        }else{
            System.out.println("This is not an active ticket" + ticket.getTicketId());
            throw new RuntimeException("This is not an active ticket" + ticket.getTicketId());
        }

        ticket.setExitTime(exitTime);
        Double fees = pricingStrategy.calculateFees(ticket);
        if(PaymentStrategyFactory.getPaymentStrategy(paymentType).pay(fees)) {
            System.out.println("Payment successful for " + ticket.getTicketId());
            ticket.setPaymentStatus(Payment_Status.SUCCESS);
        }else{
            System.out.println("Payment failed for " + ticket.getTicketId());
            ticket.setPaymentStatus(Payment_Status.FAILED);
        }
        ParkingLot.getParkingLotInstance()
                .getParkingFloor(ticket.getParkingFloorId())
                .getParkingSpot(ticket.getParkingSlot())
                .vacate();

    }

    public void printStatus() {
        parkingFloors.forEach((floorId, floor) -> {
            System.out.println("Floor: " + floorId);
            floor.getParkingSpots().values().forEach(spot -> {
                System.out.println(" Spot " + spot.getId() + " [" + spot.getVehicleType() + "] - " + (!spot.isAvailable() ? "Occupied" : "Free"));
            });
        });
    }

}
