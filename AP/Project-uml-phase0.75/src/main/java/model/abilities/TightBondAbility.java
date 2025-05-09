package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;
import model.game.Row;

public class TightBondAbility extends Ability {
    public TightBondAbility() {
        super("Tight Bound Ability");
    }
    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        Row row = currentCard.getRow();
        int numberOfThisCard = 0;
        for (Card card : row.getCards()) {
            if (card.equals(currentCard)) numberOfThisCard++;
        }
        currentCard.setPower(currentCard.getPower() * numberOfThisCard);
    }
    @Override
    public String getDescription() {
        return "Place next to a card with the same name to double the strength of both cards.";
    }
}
