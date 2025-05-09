package view;

import controller.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class RegisterMenu {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public void signUpClicked(MouseEvent mouseEvent) {
        String usernameInput = username.getText();
        String passwordInput = password.getText();
        if (RegisterController.signUpUser(usernameInput, passwordInput)) {
            Stage stage = StartMain.getStageStart();
            stage.setScene(StartMain.getSceneStart());
            stage.show();
        }
    }

    public static void printAlert(Alert alert) {
        alert.showAndWait();
    }
}
