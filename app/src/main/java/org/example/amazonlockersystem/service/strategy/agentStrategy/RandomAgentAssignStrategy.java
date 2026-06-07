package org.example.amazonlockersystem.service.strategy.agentStrategy;

import org.example.amazonlockersystem.model.Agent;

import java.util.List;

public class RandomAgentAssignStrategy implements AgentAssignStrategy {

    @Override
    public Agent assign(List<Agent> agents) {
        return agents.get((int)(Math.random()*agents.size()));
    }
}
