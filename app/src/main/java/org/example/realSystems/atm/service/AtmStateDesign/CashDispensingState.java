package org.example.realSystems.atm.service.AtmStateDesign;

import org.example.realSystems.atm.model.Account;
import org.example.realSystems.atm.model.AtmStatus;
import org.example.realSystems.atm.model.Card;
import org.example.realSystems.atm.service.AtmMachine;
import org.example.realSystems.atm.service.cor.CashDispenser;
import org.example.realSystems.atm.service.cor.CashDispensingSequence;

public class CashDispensingState implements ATMState{

    private final AtmMachine atmMachine;
    private final CashDispenser cashDispenser = CashDispensingSequence.buildChain();

    CashDispensingState(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }
    @Override
    public void insertCard(String cardNumber) {

    }

    @Override
    public void verifyPin(String pin) {
    }

    @Override
    public void selectOption() {

    }

    @Override
    public void dispenseCash(Long amount) {
        Card card  = atmMachine.getCardService().getCardByCardNumber(atmMachine.getCurrentCardInserted());
        String accountNumber = atmMachine.getCardService().getAccountNumber(card.getCardNumber());
        Account account = atmMachine.getAccountService().getAccount(accountNumber);
        if(account == null){
            System.out.println("Account not found");
            return;
        }
        if(account.getBalance() < amount){
            System.out.println("Insufficient funds");
            return;
        }
        if(atmMachine.getAtm().getBalance() < amount){
            System.out.println("Insufficient funds in ATM");
            return;
        }
        if(cashDispenser.canDispense(atmMachine.getAtm(), amount)){
            cashDispenser.dispense(atmMachine.getAtm(), amount);
            Account ac = atmMachine.getAccountService().getAccount(atmMachine.getCardService().getAccountNumber(atmMachine.getCurrentCardInserted()));
            ac.setBalance(ac.getBalance() - amount);
        }else{
            System.out.println("Amount cannot be dispensed with available denominations");
        }
        atmMachine.ejectCard();
    }

    @Override
    public void ejectCard() {
        System.out.println("Ejecting Card, Moving to IDLE_STATE");
        atmMachine.setCurrentCardInserted(null);
        atmMachine.setAtmState(new IdleState(atmMachine));
    }

    @Override
    public AtmStatus getStatus() {
        return AtmStatus.CASH_DISPENSING;
    }
}
