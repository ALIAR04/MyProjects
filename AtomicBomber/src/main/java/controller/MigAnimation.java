package controller;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.*;

public class MigAnimation extends Transition {
    private Pane pane;
    private Game game;
    private Jet jet;
    private Mig mig;
    private boolean isRocketThere = false;
    private double speed;
    private int difficulty;
    private Label label;

    public MigAnimation(Pane pane, Game game, Jet jet, Mig mig) {
        this.game = game;
        this.pane = pane;
        this.jet = jet;
        this.mig = mig;
        this.difficulty = User.getCurrentUser().getMainMenuMain().getDifficulty();
        this.speed = ((double) 3) * ((double) difficulty);
        this.setCycleDuration(Duration.INDEFINITE);
        this.setCycleCount(-1);
        label = new Label();
        label.setStyle("-fx-border-color: red");
        label.setFont(new Font(35));
        label.setStyle("-fx-color-label-visible: purple");
        label.setLayoutX(400);
        label.setLayoutY(100);
        pane.getChildren().add(label);
    }

    @Override
    protected void interpolate(double frac) {
        double x = mig.getX() - speed;
        mig.setX(x);
        if (mig.getX() < -7000) {
            mig.setX(1100);
            mig.setY(mig.getRandom().nextInt(300));
        }
        if (mig.getX() < -6000) {
            label.setText("Mig is coming");
        } else {
            label.setText("");
        }

        if (!isRocketThere) {
            if (Math.sqrt(Math.pow((jet.getX() - mig.getX()), 2) + Math.pow((jet.getY() - mig.getY()), 2)) < 150 * difficulty) {
                shootRocket();
            }
        }
    }

    private void shootRocket() {
        isRocketThere = true;
        RocketMig rocketMig = new RocketMig(jet, mig);
        pane.getChildren().add(rocketMig);
        RocketMigAnimation rocketMigAnimation = new RocketMigAnimation(pane, jet, rocketMig, (int) mig.getX(), (int) mig.getY(), this);
        game.animations.add(rocketMigAnimation);
        rocketMigAnimation.play();
    }

    public boolean isRocketThere() {
        return isRocketThere;
    }

    public void setRocketThere(boolean rocketThere) {
        isRocketThere = rocketThere;
    }
}
