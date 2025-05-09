package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class LordCommanderOfTheNorthAbility extends Ability {
    public LordCommanderOfTheNorthAbility() {
        super("Lord Commander Of the North Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        killMaxPowerCardWithPowerMoreThan10(2, enemyPlayer);
    }

    @Override
    public String getDescription() {
        return "Destroy your enemy's strongest Siege unit(s) if the combined strength of all his or her Siege units is 10 or more.";
    }
}
