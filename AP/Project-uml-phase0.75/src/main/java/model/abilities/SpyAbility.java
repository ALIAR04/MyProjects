package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;

import java.util.ArrayList;
import java.util.Random;

public class SpyAbility extends Ability {
    public SpyAbility() {
        super("Spy Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player playerPlayCard, Player playerHasSpyCard) {
        ArrayList<Card> cardsInDeck = playerPlayCard.getCardsInDeck();
        ArrayList<Card> handOfPlayer = playerPlayCard.getHand();
        Random random = new Random();
        Card card1 = cardsInDeck.get(random.nextInt(cardsInDeck.size()));
        cardsInDeck.remove(card1);
        handOfPlayer.add(card1);
        Card card2 = cardsInDeck.get(random.nextInt(cardsInDeck.size()));
        cardsInDeck.remove(card2);
        handOfPlayer.add(card2);
    }

    @Override
    public String getDescription() {
        return "Place on your opponent's battlefield (counts towards your opponent's total) and draw 2 cards from your deck.";
    }

}
