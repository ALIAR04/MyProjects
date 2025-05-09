package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class BringerOfDeathAbility extends Ability {
    public BringerOfDeathAbility() {
        super("Bringer Of Death Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        doubleCardPowersExceptWhenHornPresent(0, turnPlayer);
    }

    @Override
    public String getDescription() {
        return "Double the strength of all your Close Combat units (unless a Commander's horn is also present on that row).";
    }
}
