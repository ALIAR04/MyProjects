package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.InformationOfGame;

import java.net.URL;

public class MainMenuMain extends Application {
    private Scene sceneMainMenu;
    private ProfileMain profileMain;
    private SettingMain settingMain;
    private ScoreBoardMain scoreBoardMain;
    private Wave1 wave1 = null;
    private Wave2 wave2 = null;
    private Wave3 wave3 = null;
    private InformationOfGame informationOfGame;
    private int difficulty = 1;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/MainMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        this.sceneMainMenu = scene;
    }

    public ProfileMain getProfileMain() {
        return profileMain;
    }

    public void setProfileMain(ProfileMain profileMain) {
        this.profileMain = profileMain;
    }

    public Scene getSceneMainMenu() {
        return sceneMainMenu;
    }

    public void setSettingMain(SettingMain settingMain) {
        this.settingMain = settingMain;
    }

    public void setScoreBoardMain(ScoreBoardMain scoreBoardMain) {
        this.scoreBoardMain = scoreBoardMain;
    }

    public Wave1 getWave1() {
        return wave1;
    }

    public void setWave1(Wave1 wave1) {
        this.wave1 = wave1;
    }

    public Wave2 getWave2() {
        return wave2;
    }

    public void setWave2(Wave2 wave2) {
        this.wave2 = wave2;
    }

    public Wave3 getWave3() {
        return wave3;
    }

    public void setWave3(Wave3 wave3) {
        this.wave3 = wave3;
    }

    public InformationOfGame getInformationOfGame() {
        return informationOfGame;
    }

    public void setInformationOfGame(InformationOfGame informationOfGame) {
        this.informationOfGame = informationOfGame;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
