package controller;

import javafx.stage.Stage;
import model.User;
import view.MainMenuMain;
import view.StartMain;

public class SettingController {

    public static void backToMainMenu(int difficulty) {
        User loggedIn = User.getCurrentUser();
        MainMenuMain mainMenuMain = loggedIn.getMainMenuMain();
        mainMenuMain.setDifficulty(difficulty);
        Stage stage = StartMain.getStageStart();
        stage.setScene(mainMenuMain.getSceneMainMenu());
        stage.show();
    }

    public static void mute() {
        StartMain.getMediaPlayer1().stop();
    }
}
