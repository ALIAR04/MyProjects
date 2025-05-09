package model.game;

import model.cards.definitionCards.Card;

import java.util.HashMap;

public class DeckMap extends HashMap<Card, Integer> {
    public DeckMap(){

    }
    public DeckMap(String deckMapString){
        super();
        deckMapString = deckMapString.substring(1, deckMapString.length() - 1);
        String[] cards = deckMapString.split(", ");
        for (String card: cards){
            String[] components = card.split("=");
            Card key = Card.getCardWithName(components[0]);
            int value = Integer.parseInt(components[1]);
            this.put(key, value);
        }
    }

    public DeckMap(HashMap<Card, Integer> cards) {
        for (Card card: cards.keySet()) this.put(card, cards.get(card));
    }
}
