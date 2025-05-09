package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import javax.crypto.spec.PSource;

public class FallJet extends Rectangle {
    private Pane pane;
    private Jet jet;

    public FallJet(Pane pane, Jet jet, double frac) {
        super(40 * frac, 40 * frac);
        this.jet = jet;
        this.pane = pane;
        setX(jet.getX() + 5);
        setY(jet.getY() + 5);
        setFill(new ImagePattern(new Image(FallJet.class.getResource("/Images/jetFire.png").toExternalForm())));
    }

}
