package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ExplosionTrench extends Rectangle {
    private Trench trench;
    private Game game;
    private Rocket rocket;

    public ExplosionTrench(Game game, Trench trench, Rocket rocket) {
        super(70,70);
        this.game = game;
        this.rocket = rocket;
        this.trench = trench;
        setX(trench.getX() + 15);
        setY(trench.getY() + 15);
        setFill(new ImagePattern(new Image(ExplosionTrench.class.getResource("/Images/tankFire.png").toExternalForm())));
    }
}
