package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.cards.weatherCards.TorrentialRain;
import model.game.Player;

public class TheWhiteFlameAbility extends Ability {
    public TheWhiteFlameAbility() {
        super("The White Flame Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        for (Card card: turnPlayer.getCurrentDeck().getCards().keySet()){
            if (card instanceof TorrentialRain){
                turnPlayer.put(card.clone(true));
                break;
            }
        }
    }

    @Override
    public String getDescription() {
        return "Pick a Torrential Rain card from your deck and play it instantly.";
    }
}
