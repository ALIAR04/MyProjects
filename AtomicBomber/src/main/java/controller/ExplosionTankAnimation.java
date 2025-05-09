package controller;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;

public class ExplosionTankAnimation extends Transition {
    private Rocket rocket;
    private Tank tank;
    private Game game;
    private Pane pane;
    private ExplosionTank explosionTank;

    public ExplosionTankAnimation(Game game, Tank tank, Rocket rocket, Pane pane, ExplosionTank explosionTank) {
        this.game = game;
        this.tank = tank;
        this.rocket = rocket;
        this.pane = pane;
        this.explosionTank = explosionTank;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(1));
    }


    @Override
    protected void interpolate(double frac) {

    }
}
