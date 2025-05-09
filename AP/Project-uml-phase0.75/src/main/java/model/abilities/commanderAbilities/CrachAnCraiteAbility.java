package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Deck;
import model.game.Player;

import java.util.ArrayList;


public class CrachAnCraiteAbility extends Ability {
    public CrachAnCraiteAbility() {
        super("Crach an Craite Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        Deck turnPlayerDeck = turnPlayer.getCurrentDeck();
        ArrayList<Card> turnPlayerDiscard = turnPlayer.getDiscardPile();
        Deck enemyPlayerDeck = enemyPlayer.getCurrentDeck();
        ArrayList<Card> enemyPlayerDiscard = enemyPlayer.getDiscardPile();

        returnCardsToDeck(turnPlayerDeck, turnPlayerDiscard);
        returnCardsToDeck(enemyPlayerDeck, enemyPlayerDiscard);
    }

    private void returnCardsToDeck(Deck deck, ArrayList<Card> discard) {
        for (int i = 0; i < discard.size(); i++){
            Card card = discard.get(i);
            if (!card.isHero()){
                Card card1 = deck.findCard(card);
                if (card1 != null){
                    int amount = deck.getCards().get(card1);
                    deck.getCards().put(card1, amount + 1);
                }
                else{
                    card.setAffectedByWeather(false);
                    card.setPowerToOriginal();
                    deck.getCards().put(card, 1);
                }
                discard.remove(card);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Shuffle all cards from each player's graveyard back into their decks.";
    }
}
