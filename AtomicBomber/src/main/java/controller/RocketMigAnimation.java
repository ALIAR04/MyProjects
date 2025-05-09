package controller;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;
import view.LoseWindowMain;
import view.StartMain;

public class RocketMigAnimation extends Transition {
    private Jet jet;
    private double x;
    private double y;
    private RocketMig rocketMig;
    private Pane pane;
    private double speed = 0.05;
    public MigAnimation migAnimation;

    public RocketMigAnimation(Pane pane, Jet jet, RocketMig rocketMig, int x, int y, MigAnimation migAnimation) {
        this.pane = pane;
        this.jet = jet;
        this.x = x;
        this.y = y;
        this.rocketMig = rocketMig;
        this.migAnimation = migAnimation;
        this.setCycleDuration(Duration.INDEFINITE);
        this.setCycleCount(-1);
    }



    @Override
    protected void interpolate(double frac) {
        double distance = Math.sqrt(Math.pow((rocketMig.getX() - x), 2) + Math.pow((rocketMig.getY() - y), 2));
        if (distance >= (150 * User.getCurrentUser().getMainMenuMain().getDifficulty()) && rocketMig.isItThere) {
            pane.getChildren().remove(rocketMig);
            rocketMig.isItThere = false;
            migAnimation.setRocketThere(false);
        }

        if (jet.getBoundsInParent().intersects(rocketMig.getBoundsInParent()) && rocketMig.isItThere) {
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
            pane.getChildren().remove(rocketMig);
            migAnimation.setRocketThere(false);
            rocketMig.isItThere = false;
        }
        double x = (jet.getX() - rocketMig.getX()) * speed;
        double y = (jet.getY() - rocketMig.getY()) * speed;
        rocketMig.setX(x + rocketMig.getX());
        rocketMig.setY(y + rocketMig.getY());
    }
}
