package org.example.realSystems.atm.service;

import org.example.realSystems.atm.model.Card;
import org.example.realSystems.atm.repository.CardRepository;


public class CardService {

    CardRepository  cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public Card getCardByCardNumber(String cardNumber) {
        return cardRepository.getCard(cardNumber);
    }
    public String getAccountNumber(String cardNumber) {
        return cardRepository.getAccountNo(cardNumber);
    }

    public boolean validateCard(String cardNumber, String pin) {
        Card card = cardRepository.getCard(cardNumber);
        if(card != null){
            return card.getPin().equals(pin);
        }else{
            System.out.println("Card not found");
            return false;
        }
    }
}
