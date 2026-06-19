package org.example.realSystems.parkinglot;

import org.example.realSystems.parkinglot.enums.PaymentType;
import org.example.realSystems.parkinglot.enums.Pricing_Strategy;
import org.example.realSystems.parkinglot.enums.VehicleType;
import org.example.realSystems.parkinglot.factory.PricingStrategyFactory;
import org.example.realSystems.parkinglot.model.*;
import org.example.realSystems.parkinglot.service.ParkingLot;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ParkingLotMain {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getParkingLotInstance();
        EntryGate entryGate = new EntryGate("EG1");
        ExitGate exitGate = new ExitGate("XG1");


        ParkingFloor floor1 = new ParkingFloor("Floor1");
        floor1.addParkingSpot(new ParkingSpot("F1S1", VehicleType.BIKE));
        floor1.addParkingSpot(new ParkingSpot("F1S2", VehicleType.CAR));
        floor1.addParkingSpot(new ParkingSpot("F1S3", VehicleType.TRUCK));
        floor1.addParkingSpot(new ParkingSpot("F1S4", VehicleType.CAR));
        lot.addParkingFloor(floor1);

        System.out.println("--------------------------");

        Vehicle bike1 = new Bike("KA01AB1234", VehicleType.BIKE);
        Vehicle bike2 = new Bike("KA01AB5678", VehicleType.BIKE);
        Date entryTime = new Date();
        System.out.println("Entry time : " + entryTime);

        lot.printStatus();

        Thread t1 = new Thread(() -> entryGate.parkVehicle(bike1, entryTime));
        Thread t2 = new Thread(() -> entryGate.parkVehicle(bike2, entryTime));

        t1.start();
        t2.start();

        Vehicle car = new Car("KA01AB1234", VehicleType.CAR);

        Date carEntryTime = new Date();
        Ticket ticket = entryGate.parkVehicle(car, carEntryTime);

        System.out.println("--------------------------");

        lot.printStatus();

        System.out.println("--------------------------");

        Date carExitTime = new Date();
        carExitTime.toInstant().plus(Duration.of(2, ChronoUnit.HOURS));
        exitGate.unPark(ticket, carExitTime, PaymentType.UPI);

        System.out.println("--------------------------");

        lot.printStatus();
    }


}
