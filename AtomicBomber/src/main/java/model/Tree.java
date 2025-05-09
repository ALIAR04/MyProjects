package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Tree extends Rectangle {
    private double width = 35;
    private double height = 50;
    private Game game;
    private boolean isHit = false;
    private Random random = new Random();

    public Tree(Game game) {
        super(35, 50);
        this.game = game;
        setX(random.nextInt(300) + 600);
        setY(395);
        setFill(new ImagePattern(new Image(Tree.class.getResource("/Images/tree.png").toExternalForm())));
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
