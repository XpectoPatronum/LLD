package org.example.realSystems.atm.repository;

import lombok.Data;
import org.example.realSystems.atm.model.Account;
import org.example.realSystems.atm.model.Card;

import java.util.HashMap;
import java.util.Map;

@Data
public class CardRepository {
    Map<String, Card> cardMap = new HashMap<>();

    Map<String, String> accountNoMap =  new HashMap<>();

    public void addCard(Card card) {
        cardMap.put(card.getCardNumber(),card);
    }

    public Card getCard(String cardNumber) {
        if(cardMap.containsKey(cardNumber)){
            return cardMap.get(cardNumber);
        }else{
            System.out.println("Card not found");
            return null;
        }
    }

    public void addCardToAccount(String cardNumber, String accountNo) {
        accountNoMap.put(cardNumber, accountNo);
    }

    public String getAccountNo(String cardNumber) {
        if(accountNoMap.containsKey(cardNumber)){
            return accountNoMap.get(cardNumber);
        } else{
            System.out.println("Account not found");
            return "";
        }
    }

}
