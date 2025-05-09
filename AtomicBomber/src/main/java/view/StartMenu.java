package view;

import controller.StartController;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartMenu {
    public PasswordField password;
    public TextField username;

    public void loginClicked(MouseEvent mouseEvent) {
        String usernameInput = username.getText();
        String passwordInput = password.getText();
        StartController.checkUser(usernameInput, passwordInput);
    }

    public static void printAlert(Alert alert) {
        alert.showAndWait();
    }

    public void goToRegisterMenu(MouseEvent mouseEvent) {
        RegisterMain registerMain = new RegisterMain();
        try {
            registerMain.start(StartMain.getStageStart());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = StartMain.getStageStart();
        stage.setScene(RegisterMain.sceneRegister);
        stage.show();
    }

    public void guestClicked(MouseEvent mouseEvent) {
        StartController.guest();
    }
}
