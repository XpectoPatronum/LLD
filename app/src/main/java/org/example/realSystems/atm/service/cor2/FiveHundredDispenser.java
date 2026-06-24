package org.example.realSystems.atm.service.cor2;

import org.example.realSystems.atm.model.ATM;

public class FiveHundredDispenser extends CashDispenser{

    @Override
    long handleDispense(ATM atm, long amount) {
        long count = atm.getNumberOf500s();
        long req = amount / 500;
        long mini = Math.min(count, req);
        if(mini > 0) {
            atm.setNumberOf500s(count - mini);
            System.out.println("FiveHundredDispenser dispensed " + mini + " ₹500 notes");
        }
        return amount - mini * 500;
    }
}
