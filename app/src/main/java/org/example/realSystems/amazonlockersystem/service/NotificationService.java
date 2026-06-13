package org.example.realSystems.amazonlockersystem.service;

import org.example.realSystems.amazonlockersystem.model.OtpInfo;
import org.example.realSystems.amazonlockersystem.model.Package;
import org.example.realSystems.amazonlockersystem.repository.AgentRepository;

public class NotificationService {

    AgentRepository agentRepository;

    public NotificationService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void notifyAgent(Package parcel){
        System.out.println(agentRepository.getAgentById(parcel.getAgentId()).getAgentName() + " has been assigned the parcel " + parcel.getPackageId());
    }

    public void notifyCustomer(OtpInfo otpInfo, Package pkg){
        System.out.println("Package " + pkg + " out for delivery with otp info " + otpInfo);
    }
}
