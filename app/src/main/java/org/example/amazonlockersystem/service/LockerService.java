package org.example.amazonlockersystem.service;

import org.example.amazonlockersystem.model.Locker;
import org.example.amazonlockersystem.model.Package;
import org.example.amazonlockersystem.model.Size;
import org.example.amazonlockersystem.model.Slot;
import org.example.amazonlockersystem.repository.LockerRepository;
import org.example.amazonlockersystem.service.strategy.lockerSlotStrategy.SlotStrategy;

import java.util.Collections;
import java.util.List;

public class LockerService {

    LockerRepository lockerRepository;

    SlotStrategy slotStrategy;

    public LockerService(SlotStrategy slotStrategy, LockerRepository lockerRepository){
        this.slotStrategy = slotStrategy;
        this.lockerRepository = lockerRepository;
    }

    public Locker getLockerById(String lockerId){
        return lockerRepository.getLockerById(lockerId);
    }
    public List<Locker> findLockersByZipAndSize(String zipCode, Size size){
        return lockerRepository.zipCodeToLockers.getOrDefault(zipCode, Collections.emptyList())
                .stream().filter(locker -> locker.getSlots()
                        .stream().anyMatch(slot -> slot.isAvailable() && slot.willFit(size))).toList();
    }

    private List<Slot> getAvailableSlots(Locker locker, Size size){
        return locker.getSlots()
                .stream()
                .filter(slot -> slot.isAvailable() && slot.willFit(size))
                .toList();
    }


    public String reserveSlotForPackage(Locker locker, Package parcel){
        List<Slot> availableSlots = getAvailableSlots(locker,parcel.getSize());
        Slot reservedSlot = slotStrategy.chooseSlot(availableSlots,parcel);
        if(reservedSlot == null){
            System.out.println("No Slot selected");
        }
        parcel.setSlotId(reservedSlot.getSlotId());
        parcel.setLockerId(locker.getLockerId());
        System.out.println("Reserved Slot: " + reservedSlot.getSlotId() + " \n");
        return reservedSlot.getSlotId();
    }

    public void addLocker(Locker locker){
        lockerRepository.addLocker(locker);
    }

}
