package org.example.realSystems.atm.service;

import lombok.Data;
import org.example.realSystems.atm.model.ATM;
import org.example.realSystems.atm.model.AtmStatus;
import org.example.realSystems.atm.repository.AtmRepository;
import org.example.realSystems.atm.service.AtmStateDesign.ATMState;
import org.example.realSystems.atm.service.AtmStateDesign.IdleState;
import org.example.realSystems.atm.service.cor.CashDispenser;

@Data
public class AtmMachine {
    ATM atm;
    String currentCardInserted;
    AtmRepository atmRepository;
    CardService cardService;
    AccountService accountService;
    ATMState atmState;



    public AtmMachine(ATM atm,  AtmRepository atmRepository, CardService cardService, AccountService accountService) {
        this.atm = atm;
        this.atmRepository = atmRepository;
        this.cardService = cardService;
        this.accountService = accountService;
        atmState = new IdleState(this);
    }

    public void insertCard(String cardNumber){
        atmState.insertCard(cardNumber);
    }
    public void verifyPin(String pin){
        atmState.verifyPin(pin);
    }
    public void selectOption(){
        atmState.selectOption();
    }
    public void dispenseCash(Long amount){
        atmState.dispenseCash(amount);
    }
    public void ejectCard(){
        atmState.ejectCard();
    }
    public AtmStatus getStatus(){
        return atmState.getStatus();
    }

}
