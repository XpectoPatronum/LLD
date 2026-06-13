package org.example.realSystems.atm.service.cor;

import org.example.realSystems.atm.model.ATM;

public class TwoThousandDispenser implements CashDispenser{
    private CashDispenser nextDispenser;
    @Override
    public void setNextDispenser(CashDispenser cashDispenser) {
        nextDispenser = cashDispenser;
    }

    @Override
    public boolean canDispense(ATM atm, Long amount) {
        long count = atm.getNumberOf2000s();
        long notes = Math.min(count, amount/2000);
        long remainder = amount - notes*2000;
        return ((remainder == 0) || (nextDispenser != null && nextDispenser.canDispense(atm, remainder)));
    }

    @Override
    public void dispense(ATM atm, Long amount) {
        long count = atm.getNumberOf2000s();
        long notes = Math.min(count, amount/2000);
        atm.setNumberOf2000s(count - notes);
        long remainder = amount - notes*2000;
        atm.setBalance(atm.getBalance() - notes*2000);
        if(notes > 0){
            System.out.println("Dispensed " + notes + " 2000 notes\n");
        }
        if(remainder > 0 && nextDispenser != null){
            nextDispenser.dispense(atm, remainder);
        }
    }
}
