package org.example.realSystems.amazonlockersystem.service.strategy.agentStrategy;

import org.example.realSystems.amazonlockersystem.model.Agent;

import java.util.List;

public class RandomAgentAssignStrategy implements AgentAssignStrategy {

    @Override
    public Agent assign(List<Agent> agents) {
        return agents.get((int)(Math.random()*agents.size()));
    }
}
