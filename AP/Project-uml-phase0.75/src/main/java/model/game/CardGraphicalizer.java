package model.game;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.cards.definitionCards.Card;

public class CardGraphicalizer extends Rectangle {
    
    Card card;
    
    public CardGraphicalizer() {
        super();
    }

    public CardGraphicalizer(double width, double height) {
        super(width, height);
    }

    public CardGraphicalizer(Card card) {
        setCard(card);
    }

    
    public CardGraphicalizer(Card card, double width, double height) {
        super(width, height);
        setCard(card);
    }

    public void setCard(Card card) {
        this.card = card;
        this.setFill(new ImagePattern(new Image(getClass().getResource(card.getImageAddress()).toExternalForm())));
    }

    public Card getCard() {
        return card;
    }
}
