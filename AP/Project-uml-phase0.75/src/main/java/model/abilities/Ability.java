package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;

import java.util.ArrayList;

public abstract class Ability {
    private String name;

    public Ability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public String getDescription();

    abstract public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer);
    protected boolean hasHornCard(ArrayList<Card> cards){
        boolean hasHornCard = false;
        for (Card card: cards){
            if (card.getAbility() instanceof HornAbility){
                hasHornCard = true;
                break;
            }
        }
        return hasHornCard;
    }
    protected void killMaxPowerCardWithPowerMoreThan10(int rowNum, Player enemyPlayer){
        ArrayList<Card> cards = new ArrayList<>(enemyPlayer.getRows().get(rowNum).getCards());
        Card maxPowerCard = null;
        int max = 10;
        for(Card card: cards){
            if (card.getPower() > max){
                maxPowerCard = card;
                max = maxPowerCard.getPower();
            }
        }
        if (maxPowerCard != null)
            maxPowerCard.kill(enemyPlayer, rowNum);
    }
    protected void doubleCardPowersExceptWhenHornPresent(int rowNum, Player turnPlayer){
        ArrayList<Card> cards = new ArrayList<>(turnPlayer.getRows().get(rowNum).getCards());
        if (!hasHornCard(cards)){
            for (Card card: cards){
                if (card.getPower() != -1 && !card.isHero())
                    card.doublePower();
            }
        }
    }
}
