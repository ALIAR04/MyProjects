package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ExplosionTank extends Rectangle {
    private Tank tank;
    private Game game;
    private Rocket rocket;

    public ExplosionTank(Rocket rocket, Game game, Tank tank) {
        super(40, 30);
        this.rocket = rocket;
        this.game = game;
        this.tank = tank;
        setX(tank.getX() + 5);
        setY(tank.getY() + 25);
        setFill(new ImagePattern(new Image(ExplosionTank.class.getResource("/Images/tankFire.png").toExternalForm())));
    }
}
