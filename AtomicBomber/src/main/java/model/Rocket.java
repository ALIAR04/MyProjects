package model;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Rocket extends Rectangle {
    private final double width = 25;
    private final double height = 20;

    public Rocket(Jet jet) {
        super(25, 20);
        setX(jet.getX());
        setY(jet.getY());
        setFill(new ImagePattern(new Image(Rocket.class.getResource("/Images/rocket.png").toExternalForm())));
    }
}
