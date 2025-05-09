package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class KingOfTheWildHuntAbility extends Ability {
    public KingOfTheWildHuntAbility() {
        super("King Of The Wild Hunt Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        //take one friendly card from turnPlayer's discard pile (not hero cards)
    }

    @Override
    public String getDescription() {
        return "Restore a card from your discard pile to your hand.";
    }
}
