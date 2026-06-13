package org.example.realSystems.amazonlockersystem.service.stateDesign;

public interface LockerState {
    void touch();
    void validateCode(String code, String lockerId);
    void validateOtp(String otp, String packageId);
    void closeDoor(String slotId, String lockerId, String packageId);
    void selectCarrierEntry();
}
