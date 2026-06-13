package org.example.realSystems.amazonlockersystem.service.strategy.otpStrategy;

import org.example.realSystems.amazonlockersystem.model.OtpInfo;
import org.example.realSystems.amazonlockersystem.model.Package;

import java.time.LocalDate;

public class RandomOtpGenerationStrategy implements OtpGenerationStrategy {
    @Override
    public OtpInfo generateOtp(Package parcel) {
        OtpInfo otpInfo = new OtpInfo();
        otpInfo.setValue("1234");
        otpInfo.setSlotId(parcel.getSlotId());
        otpInfo.setLockerId(parcel.getLockerId());
        LocalDate d3 = LocalDate.now();
        d3 = d3.plusDays(3);
        otpInfo.setTtl(d3);
        return otpInfo;
    }
}
