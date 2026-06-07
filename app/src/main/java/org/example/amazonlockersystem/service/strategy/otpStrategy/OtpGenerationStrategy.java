package org.example.amazonlockersystem.service.strategy.otpStrategy;

import org.example.amazonlockersystem.model.OtpInfo;
import org.example.amazonlockersystem.model.Package;

public interface OtpGenerationStrategy {
    public OtpInfo generateOtp(Package parcel);
}
