package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class HisImperialMajestyAbility extends Ability {
    public HisImperialMajestyAbility() {
        super("His Imperial Majesty Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        //shows three random cards from enemyPlayer's currentDeck.  (is currentDeck == currentHand?)
    }

    @Override
    public String getDescription() {
        return "Look at 3 random cards from your opponent's hand.";
    }
}
