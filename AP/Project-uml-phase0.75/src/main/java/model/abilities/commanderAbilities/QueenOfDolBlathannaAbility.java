package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class QueenOfDolBlathannaAbility extends Ability {
    public QueenOfDolBlathannaAbility() {
        super("Queen Of Dol Blathanna Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        killMaxPowerCardWithPowerMoreThan10(0, enemyPlayer);
    }

    @Override
    public String getDescription() {
        return "Destroy your enemy's strongest Close Combat unit(s) if the combined strength of all his or her Close Combat units is 10 or more.";
    }
}
