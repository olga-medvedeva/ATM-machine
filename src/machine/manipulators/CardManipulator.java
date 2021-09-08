package machine.manipulators;

import machine.Card;
import machine.ConsoleHelper;
import machine.exception.CardNotFoundException;

import java.util.HashSet;

public class CardManipulator {
    private static HashSet<Card> cards = new HashSet<>();

    public CardManipulator() {
    }

    static {
        cards.add(new Card("123456789000", "1231"));
        cards.add(new Card("123456789001", "1223"));
        cards.add(new Card("111111111111", "8263"));
    }

    public Card getCard(String cardNumber) {
        Card neededCard = null;
        try {
            for (Card card : cards) {
                if (card.getCardNumber().equals(cardNumber)) {
                    neededCard = card;
                }
            }
            if (neededCard == null) {
                throw new CardNotFoundException();
            }
        } catch (CardNotFoundException e) {
                ConsoleHelper.writeMessage("Card not found");
        }
        return neededCard;
    }

    public boolean checkCardNumberPattern(String cardNumber) {
        return cardNumber.trim().length() == 12 && cardNumber.matches("^[0-9]+$");
    }

    public boolean containsCard(String cardNumber) {
        for (Card card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return true;
            }
        }
        return false;
    }
}
