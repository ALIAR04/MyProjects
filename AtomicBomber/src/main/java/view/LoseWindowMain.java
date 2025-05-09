package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.InformationOfGame;

import java.net.URL;

public class LoseWindowMain extends Application {
    private int kills;
    private int score;
    private int numberOfShoots;
    private int numberOfKills;
    private int numberOfWave;
    private Pane pane;
    private InformationOfGame informationOfGame;

    public LoseWindowMain(Pane pane, InformationOfGame informationOfGame) {
        this.pane = pane;
        this.informationOfGame = informationOfGame;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoseMenu.class.getResource("/FXML/LoseMenu.fxml");
        Pane pane2 = FXMLLoader.load(url);
        Scene scene = new Scene(pane2);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public InformationOfGame getInformationOfGame() {
        return informationOfGame;
    }

    public void setInformationOfGame(InformationOfGame informationOfGame) {
        this.informationOfGame = informationOfGame;
    }
}
