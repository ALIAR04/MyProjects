package model.game;

import model.cards.definitionCards.Card;
import model.cards.definitionCards.Cards;


import java.util.ArrayList;
import java.util.HashMap;

public class Faction {
    public HashMap<Card, Integer> availableCards = new HashMap<>();
    public ArrayList<Card> availableLeaders = new ArrayList<>();
    private String name;

    public Faction(String name) {
        this.name = name;
        for (Cards card : Cards.values()) {
            try {
                if (card.getCard().getFactionName().equals(name) ||
                        card.getCard().getFactionName().equals("Neutral")) {
                    if (card.getCard().isCommander()) {
                       availableLeaders.add(card.getCard());
                    } else {
                        availableCards.put(card.getCard(), card.getCard().getNumberOfCardInFaction());
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
