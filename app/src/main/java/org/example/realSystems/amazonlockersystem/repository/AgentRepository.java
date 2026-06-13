package org.example.realSystems.amazonlockersystem.repository;

import org.example.realSystems.amazonlockersystem.model.Agent;

import java.util.*;

public class AgentRepository {
    Map<String, List<Agent>> zipToAgent = new HashMap<>();
    Map<String, Agent> idToAgent = new HashMap<>();

    public List<Agent> getAgentByZip(String zipCode){
        return zipToAgent.getOrDefault(zipCode, Collections.emptyList());
    }

    public Agent getAgentById(String agentId){
        return idToAgent.getOrDefault(agentId,null);
    }

    public void registerAgent(Agent newAgent){
        idToAgent.put(newAgent.getAgentId(),newAgent);
        for(String zip : newAgent.getServiceableZipCodes()){
            if(zipToAgent.containsKey(zip)){
                zipToAgent.get(zip).add(newAgent);
            }else{
                zipToAgent.put(zip,new ArrayList<Agent>());
                zipToAgent.get(zip).add(newAgent);
            }
        }
    }
}
