package org.example.realSystems.amazonlockersystem.service.stateDesign;

import org.example.realSystems.amazonlockersystem.model.LockerMachine;

public class IdleLockerState implements LockerState {
    LockerMachine lockerMachine;

    public IdleLockerState(LockerMachine lockerMachine){
        this.lockerMachine = lockerMachine;
    }

    @Override
    public void touch() {
        System.out.println("Screen Touched, moving to CUSTOMER PICKUP STATE");
        lockerMachine.setState(new CustomerPickupState(lockerMachine));
    }

    @Override
    public void validateCode(String code, String lockerId) {}

    @Override
    public void validateOtp(String otp, String packageId) {

    }

    @Override
    public void closeDoor(String slotId, String lockerId, String packageId) {

    }

    @Override
    public void selectCarrierEntry() {

    }


}
