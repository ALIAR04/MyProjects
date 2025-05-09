package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class RocketMig extends Rectangle {
    public boolean isItThere = true;

    public RocketMig(Jet jet, Mig mig) {
        super(30, 10);
        setX(mig.getX());
        setY(mig.getY());
        setFill(new ImagePattern(new Image(RocketTank.class.getResource("/Images/rockettank.png").toExternalForm())));
    }
}
