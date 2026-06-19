package org.example.realSystems.parkinglot.model;

import lombok.Getter;
import org.example.realSystems.parkinglot.enums.GateType;

@Getter
public abstract class Gate {
    private final String gateId;
    private GateType gateType;

    public Gate(String gateId,  GateType gateType) {
        this.gateId = gateId;
        this.gateType = gateType;
    }

    public abstract GateType getGateType();
}
