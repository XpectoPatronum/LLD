package org.example.realSystems.amazonlockersystem.service.strategy.agentStrategy;

import org.example.realSystems.amazonlockersystem.model.Agent;

import java.util.List;

public interface AgentAssignStrategy {
    Agent assign(List<Agent> agents);
}
