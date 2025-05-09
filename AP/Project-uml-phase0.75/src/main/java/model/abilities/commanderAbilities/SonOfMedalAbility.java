package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class SonOfMedalAbility extends Ability {
    public SonOfMedalAbility() {
        super("Son of Medal Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
       killMaxPowerCardWithPowerMoreThan10(1, enemyPlayer);
    }

    @Override
    public String getDescription() {
        return "Destroy your enemy's strongest Ranged Combat unit(s) if the combined strength of all his or her Ranged Combat units is 10 or more.";
    }
}
