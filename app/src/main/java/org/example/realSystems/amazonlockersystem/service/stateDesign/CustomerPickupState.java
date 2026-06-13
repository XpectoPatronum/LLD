package org.example.realSystems.amazonlockersystem.service.stateDesign;

import org.example.realSystems.amazonlockersystem.model.*;
import org.example.realSystems.amazonlockersystem.model.*;
import org.example.realSystems.amazonlockersystem.model.Package;

public class CustomerPickupState implements LockerState {
    LockerMachine machine;
    public CustomerPickupState(LockerMachine machine){
        this.machine = machine;
    }
    @Override
    public void touch() {}

    @Override
    public void validateCode(String otp, String lockerId) {}

    @Override
    public void validateOtp(String otp, String packageId) {
        if(machine.getOtpService().validateOtp(otp,packageId)){
            String slotId = machine.getPackageRepository().getPackageIdToPackage(packageId).getSlotId();
            System.out.println("OTP Validated, Pick up your package from slot " + slotId);
            machine.getOtpService().invalidateOtp(packageId);
        }else{
            System.out.println("Wrong OTP");
            throw new RuntimeException("Wrong OTP info");
        }
    }

    @Override
    public void closeDoor(String slotId, String lockerId, String packageId) {
        Locker locker = machine.getLockerService().getLockerById(lockerId);
        Slot slot = locker.getSlotById(slotId);
        Package pkg = machine.getPackageRepository().getPackageIdToPackage(packageId);
        pkg.setStatus(PackageStatus.PICKED_BY_CUSTOMER);
        slot.release();
        System.out.println("Door Closed after pick up, Switching to IDLE");
        machine.setState(new IdleLockerState(machine));
    }

    @Override
    public void selectCarrierEntry() {
        System.out.println("Switching to Carrier Entry State");
        machine.setState(new AgentDeliveryState(machine));
    }

}
