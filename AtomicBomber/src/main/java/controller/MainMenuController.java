package controller;

import javafx.animation.Transition;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Game;
import model.InformationOfGame;
import model.User;
import view.*;

public class MainMenuController {

    public static void logout() {
        Stage stage = StartMain.getStageStart();
        stage.setScene(StartMain.getSceneStart());
        stage.show();
        User.setCurrentUser(null);
    }

    public static void goToProfile() {
        User loggedIn = User.getCurrentUser();
        ProfileMain profileMain = new ProfileMain();
        try {
            profileMain.start(StartMain.getStageStart());
        } catch (Exception e) {
            e.printStackTrace();
        }
        loggedIn.getMainMenuMain().setProfileMain(profileMain);
        Stage stage = StartMain.getStageStart();
        stage.setScene(profileMain.getSceneProfile());
        stage.show();
    }

    public static void goToSetting() {
        User loggedIn = User.getCurrentUser();
        SettingMain settingMain = new SettingMain();
        try {
            settingMain.start(StartMain.getStageStart());
        } catch (Exception e) {
            e.printStackTrace();
        }
        loggedIn.getMainMenuMain().setSettingMain(settingMain);
        Stage stage = StartMain.getStageStart();
        stage.setScene(settingMain.getSceneSetting());
        stage.show();
    }

    public static void goToScoreBoard() {
        User loggedIn = User.getCurrentUser();
        ScoreBoardMain scoreBoardMain = new ScoreBoardMain();
        try {
            scoreBoardMain.start(StartMain.getStageStart());
        } catch (Exception e) {
            e.printStackTrace();
        }
        loggedIn.getMainMenuMain().setScoreBoardMain(scoreBoardMain);
        Stage stage = StartMain.getStageStart();
        stage.setScene(scoreBoardMain.getSceneScoreBoard());
        stage.show();
    }

    public static void continueGame() {
        User loggedIn = User.getCurrentUser();
        Wave1 wave1 = loggedIn.getMainMenuMain().getWave1();
        Wave2 wave2 = loggedIn.getMainMenuMain().getWave2();
        Wave3 wave3 = loggedIn.getMainMenuMain().getWave3();
        if (wave1 == null && wave2 == null && wave3 == null) {
            getAlertNoGame();
            return;
        }
        if (wave3 != null) {
            Stage stage = StartMain.getStageStart();
            stage.setScene(wave3.getScene());
            stage.show();
            Game game = wave3.getGame();
            for (Transition animation: game.animations) {
                animation.play();
            }
        } else if (wave2 != null) {
            Stage stage = StartMain.getStageStart();
            stage.setScene(wave2.getScene());
            stage.show();
            Game game = wave2.getGame();
            for (Transition animation: game.animations) {
                animation.play();
            }
        } else if (wave1 != null) {
            Stage stage = StartMain.getStageStart();
            stage.setScene(wave1.getScene());
            stage.show();
            Game game = wave1.getGame();
            for (Transition animation: game.animations) {
                animation.play();
            }
        }

    }

    private static void getAlertNoGame() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("continue game");
        alert.setContentText("there is no game to continue\nplease start a new game");
        alert.showAndWait();
    }

    public static void newGame() {
        User loggedIn = User.getCurrentUser();
        Wave1 wave1 = new Wave1(User.getCurrentUser().getUsername());
        InformationOfGame informationOfGame = new InformationOfGame();
        loggedIn.getMainMenuMain().setWave1(wave1);
        loggedIn.getMainMenuMain().setInformationOfGame(informationOfGame);
        try {
            wave1.start(StartMain.getStageStart());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
