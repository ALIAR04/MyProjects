package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class CommanderOfRedRidersAbility extends Ability {

    public CommanderOfRedRidersAbility() {
        super("Commander Of Red Riders Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        //choose a weather card from deck and play it.
    }

    @Override
    public String getDescription() {
        return "Pick any weather card from your deck and play it instantly.";
    }
}
