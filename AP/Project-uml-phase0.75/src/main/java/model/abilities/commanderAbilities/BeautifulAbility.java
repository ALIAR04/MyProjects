package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class BeautifulAbility extends Ability {
    public BeautifulAbility() {
        super("Beautiful Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        doubleCardPowersExceptWhenHornPresent(1, turnPlayer);
    }

    @Override
    public String getDescription() {
        return "Doubles the strength of all your Ranged Combat units (unless a Commander's Horn is also present on that row).";
    }
}
