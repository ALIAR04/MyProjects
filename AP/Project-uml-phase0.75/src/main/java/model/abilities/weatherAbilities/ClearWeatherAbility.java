package model.abilities.weatherAbilities;

import controller.GameController;
import model.cards.definitionCards.Card;
import model.cards.definitionCards.WeatherCard;
import model.game.Player;

import java.util.ArrayList;

public class ClearWeatherAbility extends WeatherAbility{
    public ClearWeatherAbility() {
        super("Clear Weather Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        //cancels weather abilities.
        ArrayList<Card> cards = new ArrayList<>(turnPlayer.getRows().get(2).getCards());
        cards.addAll(enemyPlayer.getRows().get(2).getCards());
        cards.addAll(turnPlayer.getRows().get(1).getCards());
        cards.addAll(enemyPlayer.getRows().get(1).getCards());
        cards.addAll(turnPlayer.getRows().get(0).getCards());
        cards.addAll(enemyPlayer.getRows().get(0).getCards());
        for (Card card: cards){
            if (card.isAffectedByWeather()){
                card.setPowerToOriginal();
                card.setAffectedByWeather(false);
            }
        }
        for (Card card: GameController.getCurrentGame().getSpellsInPlay()){
            if (card instanceof WeatherCard) card.neutralizeAbility();
        }
    }

    @Override
    public String getDescription() {
        return "Removes all Weather Cards (Biting Frost, Impenetrable Fog and Torrential Rain) effects.";
    }
}
