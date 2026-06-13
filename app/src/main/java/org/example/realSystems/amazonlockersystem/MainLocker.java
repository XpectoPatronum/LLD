package org.example.realSystems.amazonlockersystem;

import org.example.realSystems.amazonlockersystem.model.*;
import org.example.realSystems.amazonlockersystem.model.*;
import org.example.realSystems.amazonlockersystem.model.Package;
import org.example.realSystems.amazonlockersystem.repository.AgentRepository;
import org.example.realSystems.amazonlockersystem.repository.LockerRepository;
import org.example.realSystems.amazonlockersystem.repository.OtpRepository;
import org.example.realSystems.amazonlockersystem.repository.PackageRepository;
import org.example.realSystems.amazonlockersystem.service.*;
import org.example.realSystems.amazonlockersystem.service.AgentService;
import org.example.realSystems.amazonlockersystem.service.LockerService;
import org.example.realSystems.amazonlockersystem.service.NotificationService;
import org.example.realSystems.amazonlockersystem.service.OtpService;
import org.example.realSystems.amazonlockersystem.service.strategy.lockerSlotStrategy.FirstSlotFindStrategy;
import org.example.realSystems.amazonlockersystem.service.strategy.agentStrategy.RandomAgentAssignStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainLocker {
    public static void main(String[] args){
        LockerService lockerService = new LockerService(new FirstSlotFindStrategy(), new LockerRepository());
        AgentRepository agentRepository = new AgentRepository();
        PackageRepository packageRepository = new PackageRepository();
        NotificationService notificationService = new NotificationService(agentRepository);
        OtpService otpService = new OtpService(new OtpRepository());
        AgentService agentService = new AgentService(new RandomAgentAssignStrategy(), agentRepository, notificationService);

        //Agent addition
        List<String> serviceableZipCodes1 = new ArrayList<String>();
        serviceableZipCodes1.add("226016");
        serviceableZipCodes1.add("411007");

        List<String> serviceableZipCodes2 = new ArrayList<String>();
        serviceableZipCodes2.add("226016");

        Agent agent1 = new Agent("Bob","10", serviceableZipCodes1);
        Agent agent2 = new Agent("Alice", "20", serviceableZipCodes2);
        agentService.registerAgent(agent1);
        agentService.registerAgent(agent2);

        String zipCode = "226016";

        //Package creation
        Size packageSize = new Size(5,4,3);
        Package parcel = packageRepository.createPackage("pckg1",packageSize);

        //Creating Slots
        Slot slot1 = new Slot("1",new Size(1,1,1), new AtomicBoolean(true));
        Slot slot2 = new Slot("2",new Size(4,4,4), new AtomicBoolean(true));
        Slot slot3 = new Slot("3",new Size(10,10,10), new AtomicBoolean(true));
        Slot slot4 = new Slot("4",new Size(3,4,5), new AtomicBoolean(true));
        Slot slot5 = new Slot("5",new Size(100,10,10), new AtomicBoolean(true));

        List<Slot> l1 = new ArrayList<Slot>();
        l1.add(slot1);
        l1.add(slot2);

        List<Slot> l2 = new ArrayList<Slot>();
        l2.add(slot3);
        l2.add(slot4);
        l2.add(slot5);

        Locker locker1 = new Locker();
        locker1.setLockerId("L1");
        locker1.setSlots(l1);
        locker1.setZipcode("226016");

        Locker locker2 = new Locker();
        locker2.setLockerId("L2");
        locker2.setSlots(l2);
        locker2.setZipcode("226016");

        lockerService.addLocker(locker1);
        lockerService.addLocker(locker2);

        List<Locker> eligibleLockers = lockerService.findLockersByZipAndSize(zipCode,packageSize);
        System.out.println("Eligible Lockers are " + eligibleLockers);
        if(eligibleLockers.isEmpty()){
            System.out.println("No eligible lockers");
            throw new RuntimeException("No eligible lockers");
        }
        Locker chosenLocker = eligibleLockers.get(0);
        System.out.println("Chosen locker: " + chosenLocker.getLockerId() + "\n");
        parcel.setLockerId(chosenLocker.getLockerId());

        String reservedSlotId = lockerService.reserveSlotForPackage(chosenLocker,parcel);

        Agent assignedAgent = agentService.assignAgent(parcel, zipCode);
        if(assignedAgent == null){
            System.out.println("No agents available");
            throw new RuntimeException("No agents available");
        }
        System.out.println("Assigned Agent: " + assignedAgent + "\n");

        LockerMachine machine = new LockerMachine(
                lockerService,
                notificationService,
                packageRepository,
                otpService,
                chosenLocker
        );

        //AGENT DELIVERY
        machine.touch();
        machine.selectCarrierEntry();
        machine.validateCode(parcel.getPackageId());
        machine.closeDoor(reservedSlotId, parcel.getPackageId());

        //CUSTOMER PICKUP
        machine.touch();
        machine.validateOtp("1234", parcel.getPackageId());
        machine.closeDoor(reservedSlotId,parcel.getPackageId());

        System.out.println("Final Package State " + parcel.getStatus());
    }
}
