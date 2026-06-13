package org.example.realSystems.amazonlockersystem.service;

import org.example.realSystems.amazonlockersystem.model.OtpInfo;
import org.example.realSystems.amazonlockersystem.model.Package;
import org.example.realSystems.amazonlockersystem.repository.OtpRepository;

public class OtpService {

    OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository){
        this.otpRepository = otpRepository;
    }

    public OtpInfo generateOtp(Package parcel){
        return otpRepository.otpGenerate(parcel);
    }

    public boolean validateOtp(String otpToBeChecked, String packageId){
        return otpRepository.checkOtp(otpToBeChecked, packageId);
    }

    public void invalidateOtp(String packageId){
        otpRepository.invalidateOtp(packageId);
    }

}
