package controller;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.*;

public class RocketAnimation extends Transition {
    public Pane pane;
    public Game game;
    public Rocket rocket;
    private double speed = 3;
    private double speedX;
    private double speedY;
    private int duration = 100;
    private double degree;
    private InformationOfGame informationOfGame;


    public RocketAnimation(Pane pane, Game game, Rocket rocket, double degree) {
        this.pane = pane;
        this.game = game;
        this.rocket = rocket;
        this.degree = degree;
        double degreeRadian = Math.toRadians(degree);
        speedX = Math.cos(degreeRadian) * speed;
        if (speedX < 0) rocket.setScaleX(-1);
        speedY = Math.sin(degreeRadian) * speed;
        this.informationOfGame = User.getCurrentUser().getMainMenuMain().getInformationOfGame();
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));

    }

    @Override
    protected void interpolate(double frac) {
        rocket.setRotate(degree);
        double x = rocket.getX() + speedX;
        double y = rocket.getY() + speedY;
        speedY += 0.01;
        degree = Math.toDegrees(Math.atan(speedY/speedX));
        for (Node child : game.trucks.getChildren()) {
            Truck truck = (Truck) child;
            if (truck.getBoundsInParent().intersects(rocket.getBoundsInParent())) {
                if (truck.isHit) continue;
                truck.isHit = true;
                truck.getTruckAnimation().stop();
                game.trucks.getChildren().remove(truck);
                pane.getChildren().remove(rocket);
                this.stop();
                informationOfGame.setKills(informationOfGame.getKills() + 2);
                informationOfGame.setNumberOfKills(informationOfGame.getNumberOfKills() + 1);
                informationOfGame.getLabelKills().setText("kills: " + String.valueOf(informationOfGame.getKills()));
                break;
            }
        }
        for (Node child : game.tanks.getChildren()) {
            Tank tank = (Tank) child;
            if (tank.getBoundsInParent().intersects(rocket.getBoundsInParent())) {
                if (tank.isHit) continue;
                tank.isHit = true;
                tank.getTankAnimation().stop();
                pane.getChildren().remove(rocket);
                this.stop();
                informationOfGame.setKills(informationOfGame.getKills() + 5);
                informationOfGame.setNumberOfKills(informationOfGame.getNumberOfKills() + 1);
                informationOfGame.getLabelKills().setText("kills: " + String.valueOf(informationOfGame.getKills()));
                ExplosionTank explosionTank = new ExplosionTank(rocket, game, tank);
                pane.getChildren().add(explosionTank);
                ExplosionTankAnimation explosionTankAnimation = new ExplosionTankAnimation(game, tank, rocket, pane, explosionTank);
                explosionTankAnimation.play();
                explosionTankAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        game.tanks.getChildren().remove(tank);
                        pane.getChildren().remove(explosionTank);
                    }
                });

                break;
            }
        }

        for (Node child: game.trees.getChildren()) {
            Tree tree = (Tree) child;
            if (tree.getBoundsInParent().intersects(rocket.getBoundsInParent())) {
                if (tree.isHit()) continue;
                tree.setHit(true);
                game.trees.getChildren().remove(tree);
                pane.getChildren().remove(rocket);
                this.stop();
                informationOfGame.setNumberOfKills(informationOfGame.getNumberOfKills() + 1);
                break;
            }
        }

        for (Node child: game.trenches.getChildren()) {
            Trench trench = (Trench) child;
            if (trench.getBoundsInParent().intersects(rocket.getBoundsInParent())) {
                if (trench.isHit()) continue;
                trench.setHit(true);
                pane.getChildren().remove(rocket);
                this.stop();
                ExplosionTrench explosionTrench = new ExplosionTrench(game, trench, rocket);
                pane.getChildren().add(explosionTrench);
                ExplosionTrenchAnimation explosionTrenchAnimation = new ExplosionTrenchAnimation();
                explosionTrenchAnimation.play();
                explosionTrenchAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        pane.getChildren().remove(explosionTrench);
                        game.trenches.getChildren().remove(trench);
                    }
                });
                informationOfGame.setKills(informationOfGame.getKills() + 3);
                informationOfGame.getLabelKills().setText("kills: " + String.valueOf(informationOfGame.getKills()));
                informationOfGame.setNumberOfKills(informationOfGame.getNumberOfKills() + 1);
                break;
            }
        }

        for (Node child: game.buildings.getChildren()) {
            Building building = (Building) child;
            if (building.getBoundsInParent().intersects(rocket.getBoundsInParent())) {
                if (building.isHit()) continue;
                building.setHit(true);
                game.buildings.getChildren().remove(building);
                pane.getChildren().remove(rocket);
                this.stop();
                informationOfGame.setKills(informationOfGame.getKills() + 1);
                informationOfGame.getLabelKills().setText("kills: " + String.valueOf(informationOfGame.getKills()));
                informationOfGame.setNumberOfKills(informationOfGame.getNumberOfKills() + 1);
                break;
            }
        }

        for (Node child: game.migs.getChildren()) {
            Mig mig = (Mig) child;
            if (mig.getBoundsInParent().intersects(rocket.getBoundsInParent()) && mig.getX() > 0) {
                if (mig.isHit()) continue;
                mig.setHit(true);
                mig.getMigAnimation().stop();
                game.migs.getChildren().remove(mig);
                pane.getChildren().remove(rocket);
                this.stop();
                informationOfGame.setKills(informationOfGame.getKills() + 10);
                informationOfGame.getLabelKills().setText("kills: " + String.valueOf(informationOfGame.getKills()));
                informationOfGame.setNumberOfKills(informationOfGame.getNumberOfKills() + 1);
                break;
            }
        }

        rocket.setX(x);
        rocket.setY(y);
        if (rocket.getX() < 0) rocket.setX(1100);
        if (rocket.getX() > 1100) rocket.setX(0);
        if (rocket.getY() > 430) {
            Explosion explosion = new Explosion(rocket, game);
            pane.getChildren().add(explosion);
            ExplosionAnimation explosionAnimation = new ExplosionAnimation(pane, game, rocket, explosion);
            explosion.setExplosionAnimation(explosionAnimation);
            explosionAnimation.play();
            explosionAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    pane.getChildren().remove(explosion);
                }
            });
            this.stop();
            pane.getChildren().remove(rocket);
        }
    }

}
