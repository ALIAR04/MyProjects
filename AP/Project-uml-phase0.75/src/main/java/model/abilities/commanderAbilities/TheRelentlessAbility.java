package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class TheRelentlessAbility extends Ability {

    public TheRelentlessAbility() {
        super("The Relentless Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        //take one card from the enemy's discard pile (not hero cards)
    }

    @Override
    public String getDescription() {
        return "Draw a card from your opponent's discard pile.";
    }
}
