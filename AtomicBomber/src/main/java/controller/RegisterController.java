package controller;

import javafx.scene.control.Alert;
import model.User;
import view.RegisterMain;
import view.RegisterMenu;

public class RegisterController {
    public static boolean signUpUser(String username, String password) {
        boolean flag = false;
        for (User user: User.getAllUsers()) {
            if (user.getUsername().equals(username)) flag = true;
        }
        if (flag) {
            Alert usernameAlert = new Alert(Alert.AlertType.WARNING);
            usernameAlert.setHeaderText("Username Warning");
            usernameAlert.setContentText("username is used");
            RegisterMenu.printAlert(usernameAlert);
            return false;
        } else {
            User user = new User(username, password);
            return true;
        }
    }
}
