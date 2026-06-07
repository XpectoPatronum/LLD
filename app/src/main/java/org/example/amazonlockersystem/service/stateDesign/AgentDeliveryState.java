package org.example.amazonlockersystem.service.stateDesign;

import org.example.amazonlockersystem.model.*;
import org.example.amazonlockersystem.model.Package;

import java.util.Objects;

public class AgentDeliveryState implements LockerState {

    LockerMachine machine;

    public AgentDeliveryState(LockerMachine machine){
        this.machine = machine;
    }

    @Override
    public void touch() {

    }

    @Override
    public void validateCode(String packageId, String lockerId) {
        Locker locker = machine.getLockerService().getLockerById(lockerId);
        Package pkg = machine.getPackageRepository().getPackageIdToPackage(packageId);
        if(!Objects.equals(pkg.getLockerId(), lockerId)){
            System.out.println("Package is not from this locker");
            throw new RuntimeException("Something wrong!");
        }
        Slot slot = locker.getSlotById(pkg.getSlotId());
        System.out.println("Locker Slot opened for delivery "+ slot.getSlotId());
    }

    @Override
    public void validateOtp(String otp, String packageId) {

    }

    @Override
    public void closeDoor(String slotId, String lockerId, String packageId) {
        Locker locker = machine.getLockerService().getLockerById(lockerId);
        Package pkg = machine.getPackageRepository().getPackageIdToPackage(packageId);
        Slot slot = locker.getSlotById(slotId);

        slot.setStoredPackage(pkg);
        pkg.setStatus(PackageStatus.IN_LOCKER);

        OtpInfo otpInfo = machine.getOtpService().generateOtp(pkg);
        machine.getNotificationService().notifyCustomer(otpInfo,pkg);
        System.out.println("Moving to IDLE");
        machine.setState(new IdleLockerState(machine));
    }

    @Override
    public void selectCarrierEntry() {
        System.out.println("Moving to AGENT DELIVERY STATE");
        machine.setState(new AgentDeliveryState(machine));
    }
}
