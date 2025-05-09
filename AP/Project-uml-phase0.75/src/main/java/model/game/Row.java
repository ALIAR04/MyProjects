package model.game;

import model.cards.definitionCards.Card;

import java.util.ArrayList;

public class Row {
    private String name;
    private ArrayList<Card> cards = new ArrayList<>();
    private Card specialCard = null;
    private int rowNumber;
    private Player player;
    private int totalScore = 0;
    public Row(String name, Player player, int rowNumber) {
        this.name = name;
        this.player = player;
        this.rowNumber = rowNumber;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getSpecialCard() {
        return specialCard;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void updateTotalScore(){

    }

    public int getTotalScore(){
        totalScore = 0;
        for (Card card: cards) {
            if (card.getPower() > -1)
                totalScore += card.getPower();
        }
        return totalScore;
    }
    public void putCard(Card card){
        cards.add(card);
        //TODO
    }

    public Player getPlayer() {
        return player;
    }
}
