package controller;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Explosion;
import model.Game;
import model.Rocket;

public class ExplosionAnimation extends Transition {
    private Rocket rocket;
    private Game game;
    private Pane pane;
    private Explosion explosion;

    public ExplosionAnimation(Pane pane, Game game, Rocket rocket, Explosion explosion) {
        this.game = game;
        this.pane = pane;
        this.rocket = rocket;
        this.explosion = explosion;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(500));
    }


    @Override
    protected void interpolate(double frac) {

    }
}
