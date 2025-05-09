package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class HopeOfTheAenSeidheAbility extends Ability {
    public HopeOfTheAenSeidheAbility() {
        super("Hope Of The Aen Seidhe Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        //takes agile units to the row where they have maximum power

    }

    @Override
    public String getDescription() {
        return "Move agile units to whichever valid row maximizes their strength (don't move units already in optimal row).";
    }
}
