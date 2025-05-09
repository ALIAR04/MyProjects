package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;
import model.game.Row;

import java.util.ArrayList;
import java.util.HashMap;

public class MusterAbility extends  Ability {
    public MusterAbility() {
        super("Muster Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        ArrayList<Card> cardsInDeck = turnPlayer.getCardsInDeck();
        Row row = currentCard.getRow();
        for (int i = 0; i < cardsInDeck.size(); i++) {
            Card card = cardsInDeck.get(i);
            if (card.equals(currentCard)) {
                row.getCards().add(card);
                card.setRow(row);
                cardsInDeck.remove(card);
                card.neutralizeAbility();
                i--;
            }
        }
        for (int i = 0; i < turnPlayer.getHand().size(); i++) {
            Card card = turnPlayer.getHand().get(i);
            if (card.equals(currentCard)) {
                row.getCards().add(card);
                card.setRow(row);
                turnPlayer.getHand().remove(card);
                card.neutralizeAbility();
                i--;
            }
        }
        currentCard.neutralizeAbility();
    }

    @Override
    public String getDescription() {
        return "Find any cards with the same name in your deck and play them instantly.";
    }
}
