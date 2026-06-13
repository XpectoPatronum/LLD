package org.example.realSystems.amazonlockersystem.service;

import org.example.realSystems.amazonlockersystem.model.Agent;
import org.example.realSystems.amazonlockersystem.model.Package;
import org.example.realSystems.amazonlockersystem.model.PackageStatus;
import org.example.realSystems.amazonlockersystem.repository.AgentRepository;
import org.example.realSystems.amazonlockersystem.service.strategy.agentStrategy.AgentAssignStrategy;


public class AgentService {

    AgentRepository agentRepository;

    AgentAssignStrategy agentAssignStrategy;

    NotificationService notificationService;

    public AgentService(AgentAssignStrategy agentAssignStrategy, AgentRepository agentRepository, NotificationService notificationService){
        this.agentAssignStrategy = agentAssignStrategy;
        this.agentRepository = agentRepository;
        this.notificationService = notificationService;
    }

    public void registerAgent(Agent newAgent){
        agentRepository.registerAgent(newAgent);
    }

    public Agent assignAgent(Package parcel, String zipCode){
        Agent assignedAgent = agentAssignStrategy.assign(agentRepository.getAgentByZip(zipCode));
        parcel.setAgentId(assignedAgent.getAgentId());
        parcel.setStatus(PackageStatus.ASSIGNED_TO_AGENT);
        notificationService.notifyAgent(parcel);
        return assignedAgent;
    }

}
