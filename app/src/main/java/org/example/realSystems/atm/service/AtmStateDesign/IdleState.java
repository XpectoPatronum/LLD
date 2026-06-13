package org.example.realSystems.atm.service.AtmStateDesign;

import org.example.realSystems.atm.model.AtmStatus;
import org.example.realSystems.atm.service.AtmMachine;

public class IdleState implements ATMState {

    AtmMachine atmMachine;
    public IdleState(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }
    @Override
    public void insertCard(String cardNumber) {
        System.out.println("Card inserted: " + cardNumber);
        System.out.println("Moving to CARD_INSERTED_STATE");
        atmMachine.setCurrentCardInserted(cardNumber);
        atmMachine.setAtmState(new CardInsertedState(atmMachine));
    }

    @Override
    public void verifyPin(String pin) {
    }

    @Override
    public void selectOption() {

    }

    @Override
    public void dispenseCash(Long amount) {

    }

    @Override
    public void ejectCard() {

    }

    @Override
    public AtmStatus getStatus() {
        return AtmStatus.IDLE;
    }
}
