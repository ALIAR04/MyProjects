package model.abilities.weatherAbilities;

import model.cards.definitionCards.Card;
import model.game.Player;

public class SkelligeStormAbility extends WeatherAbility{
    public SkelligeStormAbility() {
        super("Skellige Storm Ability");
    }
    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        int[] arr = {1, 2};
        doAbility(arr, turnPlayer, enemyPlayer);
    }

    @Override
    public String getDescription() {
        return "Reduces the Strength of all Range and Siege Units to 1.";
    }
}
