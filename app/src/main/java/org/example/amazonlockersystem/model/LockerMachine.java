package org.example.amazonlockersystem.model;

import lombok.Data;
import org.example.amazonlockersystem.repository.PackageRepository;
import org.example.amazonlockersystem.service.LockerService;
import org.example.amazonlockersystem.service.stateDesign.IdleLockerState;
import org.example.amazonlockersystem.service.stateDesign.LockerState;
import org.example.amazonlockersystem.service.NotificationService;
import org.example.amazonlockersystem.service.OtpService;

@Data
public class LockerMachine {
    private final LockerService lockerService;
    private final NotificationService notificationService;
    private final PackageRepository packageRepository;
    private final OtpService otpService;
    private final Locker locker;

    LockerState state;

    public LockerMachine(LockerService lockerService, NotificationService notificationService, PackageRepository packageRepository, OtpService otpService, Locker locker) {
        this.lockerService = lockerService;
        this.notificationService = notificationService;
        this.packageRepository = packageRepository;
        this.otpService = otpService;
        this.locker = locker;
        state = new IdleLockerState(this);
    }



    public void touch(){
        state.touch();
    }

    public void validateOtp(String otp, String packageId){
        state.validateOtp(otp,packageId);
    }

    public void validateCode(String code){
        state.validateCode(code, locker.getLockerId());
    }

    public void closeDoor(String slotId, String packageId){
        state.closeDoor(slotId, locker.getLockerId(),packageId);
    }

    public void selectCarrierEntry(){
        state.selectCarrierEntry();
    }
}
