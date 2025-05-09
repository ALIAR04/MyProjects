package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class ScoreBoardMain extends Application {
    private Scene sceneScoreBoard;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/ScoreBoardMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        this.sceneScoreBoard = scene;
    }

    public Scene getSceneScoreBoard() {
        return sceneScoreBoard;
    }
}
