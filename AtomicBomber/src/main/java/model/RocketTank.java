package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class RocketTank extends Rectangle {
    public boolean isItThere = true;

    public RocketTank(Jet jet, Tank tank) {
        super(30, 10);
        setX(tank.getX());
        setY(tank.getY());
        setFill(new ImagePattern(new Image(RocketTank.class.getResource("/Images/rockettank.png").toExternalForm())));
    }
}
