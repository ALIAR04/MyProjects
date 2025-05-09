package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;
import view.LoseWindowMain;
import view.StartMain;

public class RocketTankAnimation extends Transition {
    private Jet jet;
    private double x;
    private double y;
    private RocketTank rocketTank;
    private Pane pane;
    private double speed = 0.03;
    public TankAnimation tankAnimation;

    public RocketTankAnimation(Pane pane, Jet jet, double x, double y, TankAnimation tankAnimation, RocketTank rocketTank) {
        this.jet = jet;
        this.pane = pane;
        this.x = x;
        this.y = y;
        this.tankAnimation = tankAnimation;
        this.rocketTank = rocketTank;
        this.setCycleDuration(Duration.INDEFINITE);
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        double distance = Math.sqrt(Math.pow((rocketTank.getX() - x), 2) + Math.pow((rocketTank.getY() - y), 2));
        if (distance >= (150 * User.getCurrentUser().getMainMenuMain().getDifficulty()) && rocketTank.isItThere) {
            pane.getChildren().remove(rocketTank);
            rocketTank.isItThere = false;
            tankAnimation.setRocketThere(false);
        }
        if (jet.getBoundsInParent().intersects(rocketTank.getBoundsInParent()) && rocketTank.isItThere) {
            InformationOfGame informationOfGame = User.getCurrentUser().getMainMenuMain().getInformationOfGame();
            if (informationOfGame.getHitPoint() == 3) {
                pane.getChildren().remove(informationOfGame.getImageHitPoint1());
            } else if (informationOfGame.getHitPoint() == 2) {
                pane.getChildren().remove(informationOfGame.getImageHitPoint2());
            } else {
                pane.getChildren().remove(informationOfGame.getImageHitPoint3());
                jet.setY(411);
            }
            informationOfGame.setHitPoint(informationOfGame.getHitPoint() - 1);
            pane.getChildren().remove(rocketTank);
            tankAnimation.setRocketThere(false);
            rocketTank.isItThere = false;
        }
        double x = (jet.getX() - rocketTank.getX()) * speed;
        double y = (jet.getY() - rocketTank.getY()) * speed;
        rocketTank.setX(x + rocketTank.getX());
        rocketTank.setY(y + rocketTank.getY());
    }

}
