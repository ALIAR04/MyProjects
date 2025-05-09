package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;

public class NullAbility extends Ability{
    public NullAbility() {
        super("Null Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {

    }

    @Override
    public String getDescription() {
        return "";
    }
}
