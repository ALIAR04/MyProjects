package model.abilities.weatherAbilities;

import model.cards.definitionCards.Card;
import model.game.Player;

public class ImpenetrableFogAbility extends WeatherAbility{
    public ImpenetrableFogAbility() {
        super("Impenetrable Fog Ability");
    }
    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        int[] arr = {1};
        doAbility(arr, turnPlayer, enemyPlayer);
    }
    
    @Override
    public String getDescription() {
        return "Sets the strength of all Ranged Combat cards to 1 for both players.";
    }
}
