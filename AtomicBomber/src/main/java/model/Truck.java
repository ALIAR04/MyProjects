package model;

import controller.TruckAnimation;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Truck extends Rectangle {
    private final double width = 80;
    private final double height = 60;
    public boolean isHit = false;
    private Game game;
    private TruckAnimation truckAnimation;
    private Random random = new Random();

    public Truck(Game game) {
        super(80, 60);
        this.game = game;
        setX(random.nextInt(1100));
        setY(400);
        setFill(new ImagePattern(new Image(Truck.class.getResource("/Images/truck.png").toExternalForm())));
    }

    public TruckAnimation getTruckAnimation() {
        return truckAnimation;
    }

    public void setTruckAnimation(TruckAnimation truckAnimation) {
        this.truckAnimation = truckAnimation;
    }
}
