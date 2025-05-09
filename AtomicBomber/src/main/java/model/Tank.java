package model;
import controller.TankAnimation;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class Tank extends Rectangle {
    private final double width = 60;
    private final double height = 60;
    public boolean isHit = false;
    private Game game;
    private TankAnimation tankAnimation;
    private Random random = new Random();

    public Tank(Game game) {
        super(60, 60);
        this.game = game;
        setX(random.nextInt(700));
        setY(400);
        setFill(new ImagePattern(new Image(Tank.class.getResource("/Images/tank.png").toExternalForm())));
    }

    public TankAnimation getTankAnimation() {
        return tankAnimation;
    }

    public void setTankAnimation(TankAnimation tankAnimation) {
        this.tankAnimation = tankAnimation;
    }

}
