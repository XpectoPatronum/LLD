package org.example.realSystems.amazonlockersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    private String agentName;
    private String agentId;
    private List<String> serviceableZipCodes;
}
