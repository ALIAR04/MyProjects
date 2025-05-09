package model.game;

import model.game.Deck;

import java.util.ArrayList;

public class DeckList extends ArrayList<Deck> {
    public DeckList(String deckListString) {
        if (deckListString.equals("DeckList{}"))
            return;
        deckListString = deckListString.substring(9, deckListString.length() - 1);
        String[] decks = deckListString.split("\\|");
        for (String deckString: decks)
            this.add(new Deck(deckString));
    }

    public DeckList() {

    }

    @Override
    public String toString() {
        if (this.size() == 0){
            return "DeckList{}";
        }
        System.out.println(this.size());
        StringBuilder deckListString = new StringBuilder();
        deckListString.append("DeckList{");
        for (Deck deck: this){
            if (this.indexOf(deck) < this.size() - 1)
                deckListString.append(deck.toString()).append("|");
            else deckListString.append(deck.toString());
        }
        deckListString.append("}");
        return deckListString.toString();
    }

}
