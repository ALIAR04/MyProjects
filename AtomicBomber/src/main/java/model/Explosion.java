package model;


import controller.ExplosionAnimation;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Explosion extends Rectangle {
    private Rocket rocket;
    private Game game;
    private ExplosionAnimation explosionAnimation;

    public Explosion(Rocket rocket, Game game) {
        super(30,30);
        this.game = game;
        this.rocket = rocket;
        setX(rocket.getX());
        setY(rocket.getY());
        setFill(new ImagePattern(new Image(Explosion.class.getResource("/Images/explosion.png").toExternalForm())));
    }


    public void setExplosionAnimation(ExplosionAnimation explosionAnimation) {
        this.explosionAnimation = explosionAnimation;
    }
}
