package model.abilities.weatherAbilities;

import model.cards.definitionCards.Card;
import model.game.Player;


public class TorrentialRainAbility extends WeatherAbility {

    public TorrentialRainAbility() {
        super("Torrential Rain Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        int[] arr = {2};
        doAbility(arr, turnPlayer, enemyPlayer);
    }

    @Override
    public String getDescription() {
        return "Sets the strength of all Siege Combat cards to 1 for both players.";
    }
}
