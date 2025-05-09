package view;

import controller.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;

public class StartMain extends Application {
    private static Stage stageStart;
    private static Scene sceneStart;
    private static MediaPlayer mediaPlayer1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartMain.stageStart = primaryStage;
        URL url = StartMenu.class.getResource("/FXML/StartMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        StartMain.sceneStart = scene;
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.getIcons().add(new Image(StartMain.class.getResource("/Images/icon.jpg").toExternalForm()));
        StartController.makeTrackReady();
    }

    public static Stage getStageStart() {
        return stageStart;
    }

    public static Scene getSceneStart() {
        return sceneStart;
    }


    public static MediaPlayer getMediaPlayer1() {
        return mediaPlayer1;
    }

    public static void setMediaPlayer1(MediaPlayer mediaPlayer1) {
        StartMain.mediaPlayer1 = mediaPlayer1;
    }

}
