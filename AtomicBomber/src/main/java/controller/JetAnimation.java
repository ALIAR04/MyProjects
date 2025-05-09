package controller;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;
import view.*;

public class JetAnimation extends Transition {
    private double speed = 3.6;
    private Jet jet;
    private Pane pane;
    private Game game;
    public boolean isWave1Done = false;
    public boolean isWave2Done = false;
    public boolean isWave3Done = false;
    public boolean upRelease, downRelease, rightRelease, leftRelease, shootRelease;
    private InformationOfGame informationOfGame;

    public JetAnimation(Pane pane, Game game, Jet jet) {
        this.jet = jet;
        this.game = game;
        this.pane = pane;
        this.setCycleDuration(Duration.INDEFINITE);
        this.setCycleCount(-1);
        informationOfGame = User.getCurrentUser().getMainMenuMain().getInformationOfGame();
    }


    @Override
    protected void interpolate(double frac) {
        if (jet.getY() > 410) {
            this.stop();
            FallJetAnimation fallJetAnimation = new FallJetAnimation(pane, jet);
            fallJetAnimation.play();
            fallJetAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    LoseWindowMain loseWindowMain = new LoseWindowMain(pane, informationOfGame);
                    try {
                        loseWindowMain.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        double x, y;
        double xToRadian = Math.toRadians(jet.getDegree());
        x = speed * Math.cos(xToRadian);
        y = speed * Math.sin(xToRadian);
        jet.setX(jet.getX() + x);
        jet.setY(jet.getY() + y);
        if (jet.getX() < 0) jet.setX(1100);
        if (jet.getX() > 1100) jet.setX(0);
        if (jet.getY() < 0) {
            jet.setY(20);
        }

        int absDegree = jet.getDegree() % 360;
        if (absDegree < 0) absDegree += 360;
        if (rightRelease) {
            jet.setX(jet.getX() + 3);
            if (absDegree == 0) jet.setDegree(jet.getDegree());
            else if (absDegree < 180) jet.setDegree(jet.getDegree() - 2);
            else if (absDegree >= 180) jet.setDegree(jet.getDegree() + 2);
            jet.setRotate(jet.getDegree());
        }

        if (leftRelease) {
            jet.setX(jet.getX() - 3);
            if (absDegree < 180) jet.setDegree(jet.getDegree() + 2);
            else if (absDegree == 0) jet.setDegree(jet.getDegree());
            else if (absDegree > 180) jet.setDegree(jet.getDegree() - 2);
            jet.setRotate(jet.getDegree());
        }

        if (upRelease) {
            jet.setY(jet.getY() - 3);
            if (absDegree <= 90) jet.setDegree(jet.getDegree() - 2);
            else if (absDegree > 90 && absDegree < 270) jet.setDegree(jet.getDegree() + 2);
            else if (absDegree == 270) jet.setDegree(jet.getDegree());
            else if (absDegree > 270) jet.setDegree(jet.getDegree() - 2);
            jet.setRotate(jet.getDegree());
        }

        if (downRelease) {
            jet.setY(jet.getY() + 3);
            if (absDegree < 90) jet.setDegree(jet.getDegree() + 2);
            else if (absDegree == 90) jet.setDegree(jet.getDegree());
            else if (absDegree > 90 && absDegree < 270) jet.setDegree(jet.getDegree() - 2);
            else if (absDegree >= 270) jet.setDegree(jet.getDegree() + 2);
            jet.setRotate(jet.getDegree());
        }

        if (shootRelease) {
            User.getCurrentUser().getMainMenuMain().getInformationOfGame().setNumberOfShoots(User.getCurrentUser().getMainMenuMain().getInformationOfGame().getNumberOfShoots() + 1);
            Rocket rocket = new Rocket(jet);
            int indexOfJet = pane.getChildren().indexOf(jet);
            pane.getChildren().add(indexOfJet, rocket);
            RocketAnimation rocketAnimation = new RocketAnimation(pane, game, rocket, jet.getDegree());
            game.animations.add(rocketAnimation);
            rocketAnimation.play();
            shootRelease = false;
        }

    }


    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isWave1Done() {
        return isWave1Done;
    }

    public void setWave1Done(boolean wave1Done) {
        isWave1Done = wave1Done;
    }
}
