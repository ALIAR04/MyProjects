package controller;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Trench;

public class ExplosionTrenchAnimation extends Transition {

    public ExplosionTrenchAnimation() {
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(1));
    }
    @Override
    protected void interpolate(double frac) {

    }
}
