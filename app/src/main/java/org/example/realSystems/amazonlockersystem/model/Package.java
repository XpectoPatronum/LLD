package org.example.realSystems.amazonlockersystem.model;

import lombok.Data;

@Data
public class Package {
    private String packageId;
    private Size size;
    private String customerId;
    private String agentId;
    private String lockerId;
    private String slotId;
    private PackageStatus status;
}
