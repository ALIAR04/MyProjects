package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;
import model.game.Row;

public class MardoemeAbility extends Ability {
    public MardoemeAbility() {
        super("Mardoeme");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        Row row = currentCard.getRow();
        for (int i = 0; i < row.getCards().size(); i++) {
            Card card = row.getCards().get(i);
            if (card.getAbility() instanceof BerserkerAbility) {
                card.doAbility(turnPlayer, enemyPlayer);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Triggers transformation of all Berserker cards on the same row.";
    }
}
