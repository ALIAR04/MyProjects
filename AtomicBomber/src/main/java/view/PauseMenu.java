package view;

import controller.PauseController;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

public class PauseMenu {
    public void saveAndExitClicked(MouseEvent mouseEvent) {
        PauseController.exitWithSave();
    }

    public void exitWithoutSaveClicked(MouseEvent mouseEvent) {
        PauseController.exitWithoutSave();
    }

    public void continueOutPause(MouseEvent mouseEvent) {
        PauseController.backToGame();
    }

    public void mute(MouseEvent mouseEvent) {
        StartMain.getMediaPlayer1().stop();
    }
}
