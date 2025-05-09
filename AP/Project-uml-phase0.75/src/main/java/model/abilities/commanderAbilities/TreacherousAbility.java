package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.abilities.SpyAbility;
import model.cards.definitionCards.Card;
import model.game.Player;
import model.game.Row;

import java.util.ArrayList;

public class TreacherousAbility extends Ability {
    public TreacherousAbility() {
        super("Treacherous Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        ArrayList<Row> rows = turnPlayer.getRows();
        rows.addAll(enemyPlayer.getRows());
        for (Row row: rows){
            ArrayList<Card> cards = row.getCards();
            for (Card card: cards){
                if (card.getAbility() instanceof SpyAbility && !card.isHero())
                    card.doublePower();
            }
        }
    }

    @Override
    public String getDescription() {
        return "Doubles the strength of all spy cards (affects both players).";
    }
}
