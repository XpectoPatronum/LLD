package org.example.realSystems.atm.service.AtmStateDesign;

import org.example.realSystems.atm.model.AtmStatus;
import org.example.realSystems.atm.model.Card;
import org.example.realSystems.atm.service.AtmMachine;

public class CardInsertedState implements ATMState {

    private AtmMachine atmMachine;

    public CardInsertedState(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard(String cardNumber) {}

    @Override
    public void verifyPin(String pin) {
        Card insertedCard = atmMachine.getCardService().getCardByCardNumber(atmMachine.getCurrentCardInserted());
        if(atmMachine.getCardService().validateCard(insertedCard.getCardNumber(),pin)){
            atmMachine.setAtmState(new AuthenticatedState(atmMachine));
            System.out.println("Card Pin Verified Successfully, Moving to AUTHENTICATED_STATE");
        }
        else{
            System.out.println("Invalid card pin");
            ejectCard();
        }
    }

    @Override
    public void selectOption() {

    }

    @Override
    public void dispenseCash(Long amount) {

    }

    @Override
    public void ejectCard() {
        System.out.println("Ejecting Card, Moving to null state");
        atmMachine.setCurrentCardInserted(null);
        atmMachine.setAtmState(new IdleState(atmMachine));
    }

    @Override
    public AtmStatus getStatus() {
        return AtmStatus.CARD_INSERTED;
    }
}
