package org.example.realSystems.amazonlockersystem.service.strategy.otpStrategy;

import org.example.realSystems.amazonlockersystem.model.OtpInfo;
import org.example.realSystems.amazonlockersystem.model.Package;

public interface OtpGenerationStrategy {
    public OtpInfo generateOtp(Package parcel);
}
