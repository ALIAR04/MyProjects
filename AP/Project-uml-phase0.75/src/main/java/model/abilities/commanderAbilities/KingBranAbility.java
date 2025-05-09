package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class KingBranAbility extends Ability {
    public KingBranAbility() {
        super("King Bran Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {

    }

    @Override
    public String getDescription() {
        return "Units only lose half their Strength in bad weather conditions.";
    }
}
