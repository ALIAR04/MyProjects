package controller;

import javafx.animation.Transition;
import javafx.stage.Stage;
import model.Game;
import model.User;
import view.Wave1;
import view.StartMain;
import view.Wave2;
import view.Wave3;

public class PauseController {

    public static void backToGame() {
        User loggedIn = User.getCurrentUser();
        Wave1 wave1 = loggedIn.getMainMenuMain().getWave1();
        Wave2 wave2 = loggedIn.getMainMenuMain().getWave2();
        Wave3 wave3 = loggedIn.getMainMenuMain().getWave3();
        Stage stage = StartMain.getStageStart();
        if (wave3 != null) {
            stage.setScene(loggedIn.getMainMenuMain().getWave3().getScene());
            stage.show();
            Game game = wave3.getGame();
            for (Transition animation: game.animations) {
                animation.play();
            }
        } else if (wave2 != null) {
            stage.setScene(loggedIn.getMainMenuMain().getWave2().getScene());
            stage.show();
            Game game = wave2.getGame();
            for (Transition animation: game.animations) {
                animation.play();
            }
            wave2.createTimeLineTruck.play();
            wave2.createTimelineTank.play();
        } else if (wave1 != null) {
            stage.setScene(loggedIn.getMainMenuMain().getWave1().getScene());
            stage.show();
            Game game = wave1.getGame();
            for (Transition animation: game.animations) {
                animation.play();
            }
        }

    }

    public static void exitWithoutSave() {
        Stage stage = StartMain.getStageStart();
        stage.setScene(User.getCurrentUser().getMainMenuMain().getSceneMainMenu());
        stage.show();
        User.getCurrentUser().getMainMenuMain().setWave1(null);
        User.getCurrentUser().getMainMenuMain().setWave2(null);
        User.getCurrentUser().getMainMenuMain().setWave3(null);
    }

    public static void exitWithSave() {
        Stage stage = StartMain.getStageStart();
        stage.setScene(User.getCurrentUser().getMainMenuMain().getSceneMainMenu());
        stage.show();

    }
}
