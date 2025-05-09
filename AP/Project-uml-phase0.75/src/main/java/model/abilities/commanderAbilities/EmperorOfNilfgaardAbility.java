package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class EmperorOfNilfgaardAbility extends Ability {
    public EmperorOfNilfgaardAbility() {
        super("Emperor Of Nilfgaard Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        enemyPlayer.getCommander().neutralizeAbility();
    }

    @Override
    public String getDescription() {
        return "Cancel your opponent's Leader Ability.";
    }
}
