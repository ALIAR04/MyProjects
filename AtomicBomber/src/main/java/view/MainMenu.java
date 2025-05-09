package view;

import controller.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.User;

public class MainMenu {

    @FXML
    private Label username;

    @FXML
    public void initialize() {
        username.setText(User.getCurrentUser().getUsername());
    }

    public void logoutClicked(MouseEvent mouseEvent) {
        MainMenuController.logout();
    }

    public void profileClicked(MouseEvent mouseEvent) {
        MainMenuController.goToProfile();
    }

    public void settingClicked(MouseEvent mouseEvent) {
        MainMenuController.goToSetting();
    }

    public void scoreBoardClicked(MouseEvent mouseEvent) {
        MainMenuController.goToScoreBoard();
    }

    public void continueClicked(MouseEvent mouseEvent) {
        MainMenuController.continueGame();
    }

    public void newGameClicked(MouseEvent mouseEvent) {
        MainMenuController.newGame();
    }
}
