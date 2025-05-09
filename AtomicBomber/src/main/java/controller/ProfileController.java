package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import view.AvatarMain;
import view.ProfileMenu;
import view.StartMain;

public class ProfileController {
    public static void changeInformation(String username, String password) {
        Alert alert = getAlertConfirmation();
        ProfileMenu.printAlert(alert);
        if (alert.getResult().getButtonData().isCancelButton()) return;
        User loggedIn = User.getCurrentUser();
        for (User user: User.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                ProfileMenu.printAlert(getAlertWarning());
                return;
            }
        }
        loggedIn.setUsername(username);
        loggedIn.setPassword(password);
        ProfileMenu.printAlert(getAlertChangeOk());
    }

    public static Alert getAlertConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("changing information");
        alert.setContentText("Are you sure?");
        return alert;
    }

    public static Alert getAlertWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Username Warning");
        alert.setContentText("username is used");
        return alert;
    }

    public static Alert getAlertChangeOk() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Change Information");
        alert.setContentText("Information changed successfully");
        return alert;
    }

    public static void goToAvatar() {
        User loggedIn = User.getCurrentUser();
        AvatarMain avatarMain = new AvatarMain();
        try {
            avatarMain.start(StartMain.getStageStart());
        } catch (Exception e) {
            e.printStackTrace();
        }
        loggedIn.getMainMenuMain().getProfileMain().setAvatarMain(avatarMain);
        Stage stage = StartMain.getStageStart();
        stage.setScene(avatarMain.getSceneAvatar());
        stage.show();
    }

    public static void deleteAccount() {
        Alert alert = getAlertConfirmation();
        ProfileMenu.printAlert(alert);
        if (alert.getResult().getButtonData().isCancelButton()) return;
        User loggedIn = User.getCurrentUser();
        Stage stage = StartMain.getStageStart();
        stage.setScene(StartMain.getSceneStart());
        stage.show();
        User.deleteUser(loggedIn);
    }

    public static void leaveAccount() {
        Stage stage = StartMain.getStageStart();
        stage.setScene(StartMain.getSceneStart());
        stage.show();
    }

    public static void backToMainMenu() {
        Stage stage = StartMain.getStageStart();
        User loggedIn = User.getCurrentUser();
        Scene scene = loggedIn.getMainMenuMain().getSceneMainMenu();
        stage.setScene(scene);
        stage.show();
    }
}
