package model.abilities.weatherAbilities;

import model.cards.definitionCards.Card;
import model.game.Player;

public class BitingFrostAbility extends WeatherAbility {
    public BitingFrostAbility() {
        super("Biting Frost Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        int[] arr = {0};
        doAbility(arr, turnPlayer, enemyPlayer);
    }

    @Override
    public String getDescription() {
        return "Sets the strength of all Close Combat cards to 1 for both players.";
    }
}
