package org.example.realSystems.amazonlockersystem.service.stateDesign;

public interface LockerState {
    void touch();
    void validateCode(String code, String lockerId);
    void validateOtp(String otp, String packageId);
    void closeDoor(String slotId, String lockerId, String packageId);
    void selectCarrierEntry();

    /*
        TODO: can consider making another base interface that implements this which says that lets
         say you have validateOtp method working in a specific implementation and for others we can
         say, not in the correct state. So then, only the required methods will be implemented by
         the state implementation and this goes inline with the Liskov Substitution Principle as well
    */
}
