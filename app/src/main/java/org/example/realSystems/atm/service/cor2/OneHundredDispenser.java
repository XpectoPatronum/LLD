package org.example.realSystems.atm.service.cor2;

import org.example.realSystems.atm.model.ATM;

public class OneHundredDispenser extends CashDispenser{
    @Override
    long handleDispense(ATM atm, long amount) {
        long count = atm.getNumberOf100s();
        long req = amount / 100;
        long mini = Math.min(count, req);
        if(mini > 0) {
            atm.setNumberOf500s(count - mini);
            System.out.println("OneHundredDispenser dispensed " + mini + " ₹100 notes");
        }
        return amount - mini * 100;
    }
}
