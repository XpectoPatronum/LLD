package org.example.realSystems.atm.service.AtmStateDesign;

import org.example.realSystems.atm.model.AtmStatus;

public interface ATMState {
    void insertCard(String cardNumber);
    void verifyPin(String pin);
    void selectOption();
    void dispenseCash(Long amount);
    void ejectCard();
    AtmStatus getStatus();
}
