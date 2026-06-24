package org.example.realSystems.atm.service.cor2;

import org.example.realSystems.atm.model.ATM;

public abstract class CashDispenser {
    protected CashDispenser nextDispenser;

    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    public synchronized void dispense(ATM atm, long amount){
        long remainder = handleDispense(atm,amount);

        if(remainder > 0){
            if(nextDispenser != null){
                nextDispenser.dispense(atm,remainder);
            }else{
                throw new IllegalStateException("Not enough notes");
            }
        }
    }

    abstract long handleDispense(ATM atm, long amount);
}
