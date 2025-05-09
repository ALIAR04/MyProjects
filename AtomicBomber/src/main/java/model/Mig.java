package model;

import controller.MigAnimation;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Mig extends Rectangle {
    public double width = 50;
    public double height = 40;
    private boolean isHit = false;
    private Game game;
    private MigAnimation migAnimation;
    private Random random = new Random();

    public Mig(Game game) {
        super(50, 40);
        this.game = game;
        setX(1100);
        setY(random.nextInt(200));
        setFill(new ImagePattern(new Image(Mig.class.getResource("/Images/mig.png").toExternalForm())));
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public MigAnimation getMigAnimation() {
        return migAnimation;
    }

    public void setMigAnimation(MigAnimation migAnimation) {
        this.migAnimation = migAnimation;
    }

    public Random getRandom() {
        return random;
    }
}
