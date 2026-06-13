package org.example.realSystems.amazonlockersystem.repository;

import org.example.realSystems.amazonlockersystem.model.OtpInfo;
import org.example.realSystems.amazonlockersystem.model.Package;
import org.example.realSystems.amazonlockersystem.service.strategy.otpStrategy.OtpGenerationStrategy;
import org.example.realSystems.amazonlockersystem.service.strategy.otpStrategy.RandomOtpGenerationStrategy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OtpRepository {
    Map<String, OtpInfo> packageToOtp = new HashMap<>();
    OtpGenerationStrategy otpGenerationStrategy = new RandomOtpGenerationStrategy();

    public void saveOtp(String packageId, OtpInfo otpInfo){
        packageToOtp.put(packageId,otpInfo);
    }

    public OtpInfo otpGenerate(Package parcel){
        OtpInfo otpInfo = otpGenerationStrategy.generateOtp(parcel);
        saveOtp(parcel.getPackageId(),otpInfo);
        return otpInfo;
    }

    public boolean checkOtp(String otp, String packageId){
        OtpInfo real = packageToOtp.get(packageId);
        System.out.println(otp +" " + real + " " + real.getTtl()+ " " + LocalDate.now());
        return (Objects.equals(real.getValue(), otp) && (LocalDate.now().isBefore(real.getTtl())));
    }

    public void invalidateOtp(String packageId){
        packageToOtp.remove(packageId);
    }
}
