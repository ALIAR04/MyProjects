package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;
import model.game.Row;

public class HornAbility extends Ability {

    public HornAbility() {
        super("Horn Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        //doubles the strength of all cards in its row.
        Row row = currentCard.getRow();
        for (Card card: row.getCards()){
            if (card.isUnit() && !card.isHero())
                card.doublePower();
        }
    }

    @Override
    public String getDescription() {
        return "Doubles the strength of all unit cards in that row. Limited to 1 per row.";
    }
}
