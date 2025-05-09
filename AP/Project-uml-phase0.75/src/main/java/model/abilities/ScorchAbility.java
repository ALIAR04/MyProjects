package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;
import model.game.Row;


import java.util.ArrayList;

public class ScorchAbility extends Ability {
    public ScorchAbility() {
        super("Scorch Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        //Remove card(s) with the maximum power points in the field (ignores heroes);
        ArrayList<Row> rows = new ArrayList<>(turnPlayer.getRows());
        rows.addAll(enemyPlayer.getRows());
        int maxPower = 0;
        for (Row row: rows){
            ArrayList<Card> cards = row.getCards();
            for (Card card : cards) {
                if (card.getPower() > maxPower)
                    maxPower = card.getPower();
            }
        }
        for (Row row: rows){
            ArrayList<Card> cards = row.getCards();
            for (Card card : cards) {
                if (card.getPower() == maxPower && !card.isHero()) {
                    card.kill(row.getPlayer(), row.getRowNumber());
                }
            }
        }
    }

    public int doAbility() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Discard after playing. Kills the strongest card(s) on the battlefield.";
    }
}
