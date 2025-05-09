package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Deck;
import model.game.Player;

import java.util.ArrayList;

public class DestroyerOfWorldsAbility extends Ability {
    public DestroyerOfWorldsAbility() {
        super("Destroyer Of Worlds Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        ArrayList<Card> hand = turnPlayer.getHand();
        Deck deck = turnPlayer.getCurrentDeck();
        //kill two cards from hand and pick a card from deck
    }

    @Override
    public String getDescription() {
        return "Discard 2 card and draw 1 card of your choice from your deck.";
    }
}
