package org.example.realSystems.atm.service.cor;

import org.example.realSystems.atm.model.ATM;

public class FiveHundredDispenser implements CashDispenser {
    private CashDispenser nextDispenser;

    @Override
    public void setNextDispenser(CashDispenser cashDispenser) {
        this.nextDispenser = cashDispenser;
    }

    @Override
    public boolean canDispense(ATM atm, Long amount) {
        long count = atm.getNumberOf500s();
        long notes = Math.min(count, amount/500);
        long remainder = amount - notes*500;
        return ((remainder == 0) || (nextDispenser != null && nextDispenser.canDispense(atm, remainder)));
    }

    @Override
    public void dispense(ATM atm, Long amount) {
        long count = atm.getNumberOf500s();
        long notes = Math.min(count, amount/500);
        atm.setNumberOf500s(count - notes);
        long remainder = amount - notes*500;
        atm.setBalance(atm.getBalance() - notes*500);
        if(notes > 0){
            System.out.println("Dispensed " + notes + " 500 notes\n");
        }
        if(remainder > 0 && nextDispenser != null){
            nextDispenser.dispense(atm, remainder);
        }
    }
}
