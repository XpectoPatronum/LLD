package org.example.realSystems.carRentalSystem;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Starting Car Rental System Simulation --- \n");

        // 1. Initialize Repositories
        BranchRepository branchRepository = new BranchRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        BranchVehicleRepository branchVehicleRepository = new BranchVehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();

        // 2. Initialize Service
        RentalService rentalService = new RentalService(
                rentalRepository,
                branchRepository,
                vehicleRepository,
                branchVehicleRepository
        );

        // 3. Create and Add Branches
        Branch branch1 = new Branch("B001", "Downtown");
        Branch branch2 = new Branch("B002", "Airport");
        branchRepository.addBranch(branch1);
        branchRepository.addBranch(branch2);

        // 4. Create and Add Vehicles
        Vehicle car1 = new Vehicle("NY-1234", VehicleType.SEDAN);
        Vehicle car2 = new Vehicle("LA-5678", VehicleType.SUV);

        vehicleRepository.addVehicle(car1);
        vehicleRepository.addVehicle(car2);

        // Park vehicles initially at 'Downtown' branch
        branchVehicleRepository.addVehicle(car1, branch1);
        branchVehicleRepository.addVehicle(car2, branch1);

        System.out.println("--- Scenario 1: User 'User_A' books a SEDAN at Downtown ---");
        Rental rental = rentalService.createRental(VehicleType.SEDAN, "Downtown", "User_A");

        if (rental != null) {
            rentalRepository.addRental(rental);
            System.out.println("Vehicle status after booking (Available?): " + car1.isAvailable());
        }

        System.out.println("\n--- Simulating time passing (e.g., 3 hours later) ---");
        // Artificially creating a future date for return mapping
        long currentMillis = System.currentTimeMillis();
        Date threeHoursLater = new Date(currentMillis + (3 * 3600 * 1000));

        rental.setStartTime(new Date(currentMillis));

        System.out.println("\n--- Scenario 2: User returns vehicle at 'Airport' branch ---");
        rentalService.returnVehicle(rental, "Airport", threeHoursLater);

        System.out.println("\n--- Verification Post-Return ---");
        System.out.println("Rental Payment Status: " + rental.getPaymentStatus());
        System.out.println("Vehicle current location tracking check:");
        Branch currentBranch = branchVehicleRepository.getVehicleToBranch().get(car1.getVehicleNumber());
        System.out.println("Vehicle " + car1.getVehicleNumber() + " is now located at: " + (currentBranch != null ? currentBranch.getBranchLocation() : "Unknown"));
    }
}

// ENUMS

enum VehicleType{
    BIKE,
    SCOOTY,
    SEDAN,
    SUV,
    HATCHBACK
}

enum PaymentStatus{
    PENDING,
    SUCCESS,
    FAILURE
}


// ENTITIES

@Data
class Branch{
    final String branchId;
    final String branchLocation;
}

@RequiredArgsConstructor
@Data
class Vehicle{
    final String vehicleNumber;
    final VehicleType vehicleType;
    AtomicBoolean available = new AtomicBoolean(true);

    boolean tryBooking(){
        return available.compareAndSet(true,false);
    }

    void returnVehicle(){
        available.set(true);
    }

    boolean isAvailable(){
        return available.get();
    }
}

@Data
@ToString
class Rental{
    String rentalId;
    String userId;
    String vehicleId;
    Date startTime;
    Date endTime;
    PaymentStatus paymentStatus;

    Rental(String userId, String vehicleId, Date startTime){
        this.rentalId = UUID.randomUUID().toString();
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.paymentStatus = PaymentStatus.PENDING;
    }
}


// REPOSITORY CLASSES

class VehicleRepository{
    Map<String, Vehicle> vehicles = new HashMap<>();

    void addVehicle(Vehicle vehicle){
        vehicles.put(vehicle.vehicleNumber,vehicle);
    }

    Vehicle getVehicle(String vehicleNumber){
        return vehicles.get(vehicleNumber);
    }
}

@Getter
class BranchVehicleRepository{
    Map<String, List<Vehicle>> vehiclesInBranch = new HashMap<>();
    Map<String, Branch> vehicleToBranch = new HashMap<>();

    void addVehicle(Vehicle vehicle, Branch branch){
        vehicleToBranch.put(vehicle.vehicleNumber, branch);
        if(vehiclesInBranch.containsKey(branch.branchId)){
            vehiclesInBranch.get(branch.getBranchId()).add(vehicle);
        }else{
            List<Vehicle> list = new ArrayList<>();
            list.add(vehicle);
            vehiclesInBranch.put(branch.branchId, list);
        }
    }

    void removeVehicle(Vehicle vehicle){
        Branch branch = vehicleToBranch.get(vehicle.getVehicleNumber());
        vehicleToBranch.remove(vehicle.getVehicleNumber());
        vehiclesInBranch.get(branch.branchId).remove(vehicle);
    }

    void changeBranch(Vehicle vehicle, Branch newBranch){
        removeVehicle(vehicle);
        addVehicle(vehicle,newBranch);
    }
}


class BranchRepository{
    HashMap<String, Branch> branches = new HashMap<>();
    HashMap<String, Branch> branchFromLocation = new HashMap<>();

    void addBranch(Branch branch){
        branches.put(branch.getBranchId(),branch);
        branchFromLocation.put(branch.getBranchLocation(),branch);
    }

    void removeBranch(String branchId){
        Branch branch = branches.get(branchId);
        if(branch == null){
            System.out.println("Branch with id " + branchId + " not found");
            return;
        }
        String branchLocation = branch.getBranchLocation();
        branches.remove(branchId);
        branchFromLocation.remove(branchLocation);
    }

    Branch getBranch(String location){
        if(branchFromLocation.containsKey(location)){
            return branchFromLocation.get(location);
        }else{
            System.out.println("Branch with location " + location + " not found");
            return null;
        }
    }
}

class RentalRepository{
    Map<String, Rental> rentals = new HashMap<>();

    void addRental(Rental rental){
        rentals.put(rental.getVehicleId(),rental);
    }
}

// ****************** SERVICE LAYER ********************

class RentalService{
    RentalRepository rentalRepository;
    BranchRepository branchRepository;
    VehicleRepository vehicleRepository;
    BranchVehicleRepository branchVehicleRepository;
    PricingStrategy pricingStrategy;
    PaymentStrategy paymentStrategy;

    RentalService(RentalRepository rentalRepository, BranchRepository branchRepository, VehicleRepository vehicleRepository,  BranchVehicleRepository branchVehicleRepository){
        this.rentalRepository = rentalRepository;
        this.branchRepository = branchRepository;
        this.vehicleRepository = vehicleRepository;
        this.branchVehicleRepository = branchVehicleRepository;
        pricingStrategy = new TimeBasedPriceStrategy();
    }

    Vehicle getAvailableVehicle(VehicleType vehicleType, String location){
        Branch branch = branchRepository.getBranch(location);
        List<Vehicle> vehiclesInBranch = branchVehicleRepository.vehiclesInBranch.get(branch.getBranchId());
        for(Vehicle vehicle : vehiclesInBranch){
            if(vehicle.getVehicleType().equals(vehicleType) && vehicle.tryBooking()){
                return vehicle;
            }
        }
        System.out.println("No vehicle :" + vehicleType + " found available at " + location);
        return null;
    }

    Rental createRental(VehicleType vehicleType, String location, String userId){
        Vehicle allotedVehicle = getAvailableVehicle(vehicleType, location);
        Rental rental = new Rental(userId,allotedVehicle.vehicleNumber,new Date());
        System.out.println("Creating rental for " + allotedVehicle.vehicleNumber);
        System.out.println(rental);
        return rental;
    }

    void returnVehicle(Rental rental, String newBranchLocation, Date returnTime){
        Vehicle vehicle = vehicleRepository.getVehicle(rental.getVehicleId());
        Branch newBranch = branchRepository.getBranch(newBranchLocation);
        branchVehicleRepository.changeBranch(vehicle, newBranch);
        rental.setEndTime(returnTime);
        PaymentStrategy paymentStrategy1 = new UpiPaymentStrategy();
        if(paymentStrategy1.pay(pricingStrategy.calculateFees(rental))) {
            rental.setPaymentStatus(PaymentStatus.SUCCESS);
            vehicle.returnVehicle();
        }else{
            rental.setPaymentStatus(PaymentStatus.FAILURE);
        }
    }
}


// ****************** STRATEGY *********************

interface PaymentStrategy{
    boolean pay(double amount);
}

class UpiPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean pay(double amount) {
        System.out.println("Upi payment of $" + amount + " is completed");
        return true;
    }
}

interface PricingStrategy {
    double calculateFees(Rental rental);
}

class TimeBasedPriceStrategy implements PricingStrategy {
    @Override
    public double calculateFees(Rental rental) {
        Duration timeDuration = Duration.between(rental.getStartTime().toInstant(),rental.getEndTime().toInstant());
        long hours = timeDuration.getSeconds() / 3600;
        return 10*hours;
    }
}


