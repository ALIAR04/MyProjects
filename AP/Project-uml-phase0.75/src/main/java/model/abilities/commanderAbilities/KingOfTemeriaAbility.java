package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class KingOfTemeriaAbility extends Ability {
    public KingOfTemeriaAbility() {
        super("King of Temeria Ability");
    }
    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        doubleCardPowersExceptWhenHornPresent(2, turnPlayer);
    }

    @Override
    public String getDescription() {
        return "Doubles the strength of all your Siege units (unless a Commander's Horn is also present on that row).";
    }
}
