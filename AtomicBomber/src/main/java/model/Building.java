package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Building extends Rectangle {
    private double width = 65;
    private double height = 90;
    private boolean isHit = false;
    private Game game;
    private Random random = new Random();

    public Building(Game game) {
        super(65, 90);
        this.game = game;
        setX(random.nextInt(300) + 300);
        setY(360);
        setFill(new ImagePattern(new Image(Building.class.getResource("/Images/building.png").toExternalForm())));
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
