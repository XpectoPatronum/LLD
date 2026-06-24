package org.example.realSystems.atm.service.cor2;


public class CashDispensingSequence {
    public static CashDispenser buildChain() {
        CashDispenser cashDispenser2 = new FiveHundredDispenser();
        CashDispenser cashDispenser3 = new OneHundredDispenser();
        cashDispenser2.setNextDispenser(cashDispenser3);
        cashDispenser3.setNextDispenser(null);
        return cashDispenser2;
    }
}
