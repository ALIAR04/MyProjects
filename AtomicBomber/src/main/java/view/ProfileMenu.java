package view;

import controller.ProfileController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProfileMenu {
    @FXML
    private TextField newUsername;
    @FXML
    private TextField newPassword;

    public void changeInformation(MouseEvent mouseEvent) {
        String username = newUsername.getText();
        String password = newPassword.getText();
        ProfileController.changeInformation(username, password);
    }

    public static void printAlert(Alert alert) {
        alert.showAndWait();
    }

    public void backToMainMenuClicked(MouseEvent mouseEvent) {
        ProfileController.backToMainMenu();
    }

    public void avatarClicked(MouseEvent mouseEvent) {
        ProfileController.goToAvatar();
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        ProfileController.deleteAccount();
    }

    public void leaveAccount(MouseEvent mouseEvent) {
        ProfileController.leaveAccount();
    }
}
