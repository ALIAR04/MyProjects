package view;

import controller.WaveController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.InformationOfGame;
import model.Jet;
import model.User;

public class Wave2 extends Application {
    public final static String name = "wave2";
    public final static double width = 1100;
    public final static double height = 600;
    private Pane pane;
    private Game game;
    public Jet jet;
    private Scene scene;
    private String username;
    public Timeline createTimelineTank;
    private int durationTank = 20;
    public Timeline createTimeLineTruck;
    private int durationTruck = 15;
    private boolean isPause = false;

    public Wave2(String username) {
        this.game = new Game();
        this.username = username;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = new Pane();
        WaveController.setSize(pane);
        pane.setBackground(new Background(WaveController.createBackgroundImage()));
        jet = WaveController.createJet(game, jet, pane);
        pane.getChildren().add(jet);
        pane.getChildren().add(game.tanks);
        pane.getChildren().add(game.trucks);
        pane.getChildren().add(game.trees);
        pane.getChildren().add(game.trenches);
        pane.getChildren().add(game.buildings);
        WaveController.createBuildings(game, pane);
        WaveController.createTrees(game, pane);
        WaveController.createTrenches(game, pane);
        WaveController.createTank(game, pane, true, jet, 6);
        WaveController.createTruck(game, pane, 3);
        pane.getChildren().add(WaveController.createScoreboardWave2(this, username));
        User.getCurrentUser().getMainMenuMain().getInformationOfGame().setNameOfWave(Wave2.name);
        InformationOfGame informationOfGame = User.getCurrentUser().getMainMenuMain().getInformationOfGame();
        informationOfGame.setLabelKills(informationOfGame.createKillNumber(pane, User.getCurrentUser()));
        if (informationOfGame.getHitPoint() == 3) {
            informationOfGame.setImageHitPoint1(informationOfGame.createHitPoint1(pane, User.getCurrentUser()));
            informationOfGame.setImageHitPoint2(informationOfGame.createHitPoint2(pane, User.getCurrentUser()));
            informationOfGame.setImageHitPoint3(informationOfGame.createHitPoint3(pane, User.getCurrentUser()));
            pane.getChildren().add(informationOfGame.getImageHitPoint1());
            pane.getChildren().add(informationOfGame.getImageHitPoint2());
            pane.getChildren().add(informationOfGame.getImageHitPoint3());
        } else if (informationOfGame.getHitPoint() == 2) {
            informationOfGame.setImageHitPoint2(informationOfGame.createHitPoint2(pane, User.getCurrentUser()));
            informationOfGame.setImageHitPoint3(informationOfGame.createHitPoint3(pane, User.getCurrentUser()));
            pane.getChildren().add(informationOfGame.getImageHitPoint2());
            pane.getChildren().add(informationOfGame.getImageHitPoint3());
        } else {
            informationOfGame.setImageHitPoint3(informationOfGame.createHitPoint3(pane, User.getCurrentUser()));
            pane.getChildren().add(informationOfGame.getImageHitPoint3());
        }
        pane.getChildren().add(informationOfGame.createClusterImage());
        informationOfGame.setLabelClusters(informationOfGame.createClusterLabel(pane, User.getCurrentUser()));
        pane.getChildren().add(informationOfGame.getLabelClusters());



        Scene scene = new Scene(pane);
        this.scene = scene;
        primaryStage.setScene(scene);
        primaryStage.show();
        jet.requestFocus();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public boolean isPause() {
        return isPause;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
