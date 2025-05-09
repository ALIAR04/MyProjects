package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

import java.util.ArrayList;
import java.util.Random;


public class InvaderOfTheNorthAbility extends Ability {
    public InvaderOfTheNorthAbility() {
        super("Invader Of The North Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        ArrayList<Card> turnPlayerDiscard = turnPlayer.getDiscardPile();
        ArrayList<Card> enemyPlayerDiscard = enemyPlayer.getDiscardPile();
        Card friendlyCard = getRandomCard(turnPlayerDiscard);
        Card enemyCard = getRandomCard(enemyPlayerDiscard);
        turnPlayer.put(friendlyCard);
        enemyPlayer.put(enemyCard);
    }

    private static Card getRandomCard(ArrayList<Card> cards) {
        Random random = new Random();
        int index = random.nextInt(cards.size());
        return cards.get(index);
    }

    @Override
    public String getDescription() {
        return "Abilities that restore a unit to the battlefield restore a randomly-chosen unit. Affects both players.";
    }
}
