package model.abilities.weatherAbilities;

import model.abilities.Ability;
import model.abilities.commanderAbilities.KingBranAbility;
import model.cards.definitionCards.Card;
import model.game.Player;

import java.util.ArrayList;

public abstract class WeatherAbility extends Ability {
    public WeatherAbility(String name) {
        super(name);
    }

    @Override
    public abstract void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer);

    protected void doAbility(int[] rowNums, Player turnPlayer, Player enemyPlayer){
        for (int rowNum: rowNums){
            ArrayList<Card> cards = new ArrayList<>(turnPlayer.getRows().get(rowNum).getCards());
            cards.addAll(enemyPlayer.getRows().get(rowNum).getCards());
            Ability commanderAbility = enemyPlayer.getCommander().getAbility();
            for (Card card: cards){
                if (card.getPower() != -1 && !card.isSpecial()){
                    if (commanderAbility instanceof KingBranAbility)
                        card.setPower(card.getPower() / 2);
                    else card.setPower(1);
                    card.setAffectedByWeather(true);
                }
            }
        }
    }
}
