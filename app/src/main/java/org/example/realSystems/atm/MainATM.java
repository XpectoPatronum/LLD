package org.example.realSystems.atm;

import org.example.realSystems.atm.model.ATM;
import org.example.realSystems.atm.model.Account;
import org.example.realSystems.atm.model.AtmStatus;
import org.example.realSystems.atm.model.Card;
import org.example.realSystems.atm.repository.AccountRepository;
import org.example.realSystems.atm.repository.AtmRepository;
import org.example.realSystems.atm.repository.CardRepository;
import org.example.realSystems.atm.service.AccountService;
import org.example.realSystems.atm.service.AtmMachine;
import org.example.realSystems.atm.service.CardService;

public class MainATM {
    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepository();
        CardRepository cardRepository = new CardRepository();
        AtmRepository atmRepository = new AtmRepository();


        //account setup
        Account account1 = new Account();
        account1.setAccountNumber("123456789");
        account1.setBalance(5000L);
        Account account2 = new Account();
        account2.setAccountNumber("123456799");
        account2.setBalance(5000L);

        accountRepository.AddAccount(account1);
        accountRepository.AddAccount(account2);


        //card setup
        Card card1 = new Card();
        card1.setCardNumber("1111222233334444");
        card1.setPin("1234");
        Card card2 = new Card();
        card2.setCardNumber("5555666677778888");
        card2.setPin("5678");
        cardRepository.addCard(card1);
        cardRepository.addCard(card2);

        cardRepository.addCardToAccount(card1.getCardNumber(), account1.getAccountNumber());
        cardRepository.addCardToAccount(card2.getCardNumber(), account2.getAccountNumber());

        //Atm Setup

        ATM atm1 = new ATM();
        atm1.setAtmId("0001");
        atm1.setBalance(2000L);
        atm1.setNumberOf2000s(0L);
        atm1.setNumberOf500s(3L);
        atm1.setNumberOf100s(5L);

        ATM atm2 = new ATM();
        atm2.setAtmId("0002");
        atm2.setBalance(5000L);
        atm2.setNumberOf2000s(1L);
        atm2.setNumberOf500s(4L);
        atm2.setNumberOf100s(10L);

        atmRepository.save(atm1);
        atmRepository.save(atm2);

        AtmMachine atmMachine = new AtmMachine(atm2,atmRepository,new CardService(cardRepository),new AccountService(accountRepository));

        String userCard = "5555666677778888";
        String userPin = "5678";
        atmMachine.insertCard(userCard);
        atmMachine.verifyPin(userPin);
        atmMachine.selectOption();
        atmMachine.dispenseCash(3500L);



        atmMachine.insertCard(userCard);
        atmMachine.verifyPin(userPin);
        atmMachine.selectOption();
        atmMachine.dispenseCash(1100L);
    }
}
