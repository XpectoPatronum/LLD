package org.example.realSystems.atm.service.cor;

import org.example.realSystems.atm.model.ATM;

public class OneHundredDispenser implements CashDispenser{
    CashDispenser nextDispenser;

    @Override
    public void setNextDispenser(CashDispenser cashDispenser) {
        this.nextDispenser = cashDispenser;
    }

    @Override
    public boolean canDispense(ATM atm, Long amount) {
        long count = atm.getNumberOf100s();
        long notes = Math.min(count, amount/100);
        long remainder = amount - notes*100;
        return (remainder == 0);
    }

    @Override
    public void dispense(ATM atm, Long amount) {
        long count = atm.getNumberOf100s();
        long notes = Math.min(count, amount/100);
        atm.setNumberOf100s(count - notes);
        long remainder = amount - notes*100;
        atm.setBalance(atm.getBalance() - notes*100);
        if(notes > 0){
            System.out.println("Dispensed " + notes + " 100 notes\n");
        }
        if(remainder > 0 && nextDispenser != null){
            nextDispenser.dispense(atm, amount);
        }
    }
}
