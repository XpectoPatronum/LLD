package org.example.realSystems.atm.service.cor;

import org.example.realSystems.atm.model.ATM;

public interface CashDispenser {
    void setNextDispenser(CashDispenser cashDispenser);
    boolean canDispense(ATM atm, Long amount);
    void dispense(ATM atm, Long amount);
}
