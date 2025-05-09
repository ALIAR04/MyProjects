package controller;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;


public class TankAnimation extends Transition {
    public Pane pane;
    public Game game;
    public Tank tank;
    public Jet jet;
    private double speed;
    private int duration = 10;
    private boolean isNotWave1;
    private boolean isRocketThere;
    private int difficulty;

    public TankAnimation(Game game, Pane pane, Tank tank, boolean isNotWave1, Jet jet) {
        this.pane = pane;
        this.game = game;
        this.tank = tank;
        this.isNotWave1 = isNotWave1;
        this.jet = jet;
        difficulty = User.getCurrentUser().getMainMenuMain().getDifficulty();
        speed = ((double) 0.25) * ((double) difficulty);
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double frac) {
        if (tank.getX() > 1100) tank.setX(-5);
        double x = tank.getX() + speed;
        tank.setX(x);

        if (isNotWave1 &&!isRocketThere) {
            if (Math.sqrt(Math.pow((jet.getX() - tank.getX()), 2) + Math.pow((jet.getY() - tank.getY()), 2)) < 150 * difficulty) {
                shootRocket();
            }
        }
    }

    private void shootRocket() {
        isRocketThere = true;
        RocketTank rocketTank = new RocketTank(jet, tank);
        pane.getChildren().add(rocketTank);
        RocketTankAnimation rocketTankAnimation = new RocketTankAnimation(pane, jet, tank.getX(), tank.getY(), this, rocketTank);
        game.animations.add(rocketTankAnimation);
        rocketTankAnimation.play();
    }

    public boolean isNotWave1() {
        return isNotWave1;
    }

    public void setNotWave1(boolean notWave1) {
        isNotWave1 = notWave1;
    }

    public boolean isRocketThere() {
        return isRocketThere;
    }

    public void setRocketThere(boolean rocketThere) {
        isRocketThere = rocketThere;
    }
}
