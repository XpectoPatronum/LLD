package org.example.realSystems.atm.model;

import lombok.Data;
import org.example.realSystems.atm.service.AtmStateDesign.ATMState;

@Data
public class ATM {
    private String atmId;
    private AtmStatus atmStatus;
    private Long balance;
    private Long numberOf2000s;
    private Long numberOf500s;
    private Long numberOf100s;
}
