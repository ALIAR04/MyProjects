package controller;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.FallJet;
import model.Jet;

public class FallJetAnimation extends Transition {
    private Pane pane;
    private Jet jet;

    public FallJetAnimation(Pane pane, Jet jet) {
        this.jet = jet;
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(3));
    }

    @Override
    protected void interpolate(double frac) {
        if (frac < 0.2) {
            pane.getChildren().add(new FallJet(pane, jet, frac));
        } else if (frac < 0.4) {
            pane.getChildren().add(new FallJet(pane, jet, frac));
        } else if (frac < 0.6) {
            pane.getChildren().add(new FallJet(pane, jet, frac));
        } else if (frac < 0.8) {
            pane.getChildren().add(new FallJet(pane, jet, frac));
        } else {
            pane.getChildren().add(new FallJet(pane, jet, frac));
        }
    }
}
