package controller;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Truck;

public class TruckAnimation extends Transition {
    public Pane pane;
    public Game game;
    public Truck truck;
    private double speed = 0.2;
    private int duration = 10;

    public TruckAnimation(Game game, Pane pane, Truck truck) {
        this.game = game;
        this.pane = pane;
        this.truck = truck;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double frac) {
        if (truck.getX() < 0) truck.setX(1105);
        double x = truck.getX() - speed;
        truck.setX(x);
    }
}
