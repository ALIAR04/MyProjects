package model.game;

import model.User;
import model.abilities.TransformerAbility;
import model.cards.definitionCards.Card;

import java.util.ArrayList;

public class Player {
    private Deck currentDeck;
    private Card commander;
    private User user;
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Row> rows = new ArrayList<>();
    private int totalScore;
    private int numberOfVetosPlayed = 0;
    private int life;
    private ArrayList<Card> discardPile = new ArrayList<>();
    private ArrayList<Card> cardsInDeck = new ArrayList<>();

    public Player(User user) {
        this.totalScore = 0;
        this.life = 2;
        this.user = user;
    }

    public int getNumberOfCardsInHand(){
        return hand.size();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Deck getCurrentDeck() {
        return this.currentDeck;
    }

    public User getUser() {
        return this.user;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public void setHand(ArrayList<Card> hand){
        this.hand = hand;
        for (Card card : hand) {
            if (currentDeck.getCards().containsKey(card)) {
                currentDeck.removeCardFromDeck(card);
                cardsInDeck.remove(card);
            }
        }
    }

    public void changeCurrentHand(Card cardToReplace){
        hand.add(cardToReplace);
    }

    public ArrayList<Row> getRows() {
        return this.rows;
    }

    public int getTotalScore() {
        totalScore = 0;
        for (Row row: this.rows){
            row.updateTotalScore();
            totalScore += row.getTotalScore();
        }
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getLife() {
        return this.life;
    }

    public void decreaseLifeByOne(){
        this.life -= 1;
    }

    public ArrayList<Card> getDiscardPile() {
        return this.discardPile;
    }
    public Card getCommander() {
        return commander;
    }

    public void setCommander(Card commander) {
        this.commander = commander;
    }

    public void put(Card card) {

    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    public void addToDiscardPile(Card card) {
        if (card.getAbility() instanceof TransformerAbility) {
            card.setName(card.getTransformName());
            card.setPower(card.getBeforeTransformPower());
            card.setOriginalPower(card.getBeforeTransformPower());
            //fill the picture with before transform
        }
        discardPile.add(card);
//        TODO
    }
    public Card getCardInHandByName(String name) {
        for (Card card : hand) {
            if (card.getName().equals(name))
                return card;
        }
        return null;
    }
    public void setCurrentDeck(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    public ArrayList<Card> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<Card> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;

    }

    public int getNumberOfVetosPlayed() {
        return numberOfVetosPlayed;
    }
    public void addNumberOfVetoesPlayedByOne(){
        numberOfVetosPlayed++;
    }

    public void setRows() {
        rows.add(new Row("close combat", this, 1));
        rows.add(new Row("ranged", this, 2));
        rows.add(new Row("siege", this, 3));
    }

    public void setLife(int i) {
        this.life = i;
    }
}
