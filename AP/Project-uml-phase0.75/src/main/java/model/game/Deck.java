package model.game;


import model.cards.definitionCards.Card;
import model.cards.definitionCards.CommanderCard;

import java.util.HashMap;

public class Deck {
    private DeckMap deck = new DeckMap();
    private Faction faction ;
    private CommanderCard commanderCard;
    public Deck(){

    }
    public Deck(String deckString) {
        deckString = deckString.substring(5, deckString.length() - 1);
        String[] components = deckString.split("--");
        String deckMapString = components[0].substring(8);
        deck = new DeckMap(deckMapString);
        String factionString = components[1].substring(8);
        faction = new Faction(factionString);
        String commanderName = components[2].substring(14);
        commanderCard = (CommanderCard) Card.getCardWithName(commanderName);
    }

    public Deck(Deck currentDeck) {
        deck = new DeckMap(currentDeck.getCards());
        this.faction = currentDeck.getFaction();
        this.commanderCard = currentDeck.commanderCard;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "DeckMap:" + deck.toString() +
                "--faction:" + faction.toString() +
                "--commanderCard:" + commanderCard.getName() +
                '}';
    }

//    private String name;
//
//    public Deck(String name) {
//        this.name = name;
//    }

    public void addCardToDeck(Card card) {
        if (deck.containsKey(card)) {
            deck.put(card, deck.get(card) + 1);
        } else {
            deck.put(card, 1);
        }
    }

    public void removeCardFromDeck(Card card) {
        deck.put(card, deck.get(card) - 1);
        if(deck.get(card) == 0) {
            deck.remove(card);
        }
    }

    public HashMap<Card, Integer> getCards() {
        return this.deck;
    }

    public Faction getFaction() {
        return this.faction;
    }

    public Card findCard(Card card) {
        boolean ans = false;
        for (Card card1 : deck.keySet()) {
            if (card1.getName().equals(card.getName()))
                return card1;
        }
        return null;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public CommanderCard getCommanderCard() {
        return this.commanderCard;
    }

    public void setCommanderCard(CommanderCard commanderCard) {
        this.commanderCard = commanderCard;
    }
    public int getCardCount(Card card) {
        return deck.getOrDefault(card, 0);
    }
}
