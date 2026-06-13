package org.example.realSystems.atm.service.AtmStateDesign;

import org.example.realSystems.atm.model.AtmStatus;
import org.example.realSystems.atm.service.AtmMachine;
import org.example.realSystems.atm.service.CardService;

public class AuthenticatedState implements ATMState {
    CardService cardService;

    AtmMachine atmMachine;
    public AuthenticatedState(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard(String cardNumber) {}

    @Override
    public void verifyPin(String pin) {
        System.out.println("Already verified");
    }

    @Override
    public void selectOption() {
        System.out.println("Selected Card Withdrawal, moving to CASH_DISPENSING_STATE");
        atmMachine.setAtmState(new CashDispensingState(atmMachine));
    }

    @Override
    public void dispenseCash(Long amount) {
        System.out.println("First select option then only can dispense");
    }

    @Override
    public void ejectCard() {
        System.out.println("Ejecting Card, Moving to null state");
        atmMachine.setCurrentCardInserted(null);
        atmMachine.setAtmState(new IdleState(atmMachine));
    }

    @Override
    public AtmStatus getStatus() {
        return AtmStatus.AUTHENTICATED;
    }
}
