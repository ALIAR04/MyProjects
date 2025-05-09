package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Trench extends Rectangle {
     private double width = 80;
     private double height = 80;
     private Game game;
     private boolean isHit = false;
     private Random random = new Random();


    public Trench(Game game) {
        super(80, 80);
        this.game = game;
        setX(random.nextInt(200));
        setY(365);
        setFill(new ImagePattern(new Image(Trench.class.getResource("/Images/trench.png").toExternalForm())));
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
