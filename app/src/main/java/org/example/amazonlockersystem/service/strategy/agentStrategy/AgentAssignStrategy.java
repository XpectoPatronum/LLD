package org.example.amazonlockersystem.service.strategy.agentStrategy;

import org.example.amazonlockersystem.model.Agent;

import java.util.List;

public interface AgentAssignStrategy {
    Agent assign(List<Agent> agents);
}
