package org.example.realSystems.atm.service.cor;


public class CashDispensingSequence {
    public static CashDispenser buildChain() {
        CashDispenser cashDispenser1 = new TwoThousandDispenser();
        CashDispenser cashDispenser2 = new FiveHundredDispenser();
        CashDispenser cashDispenser3 = new OneHundredDispenser();
        cashDispenser1.setNextDispenser(cashDispenser2);
        cashDispenser2.setNextDispenser(cashDispenser3);
        cashDispenser3.setNextDispenser(null);
        return cashDispenser1;
    }
}
