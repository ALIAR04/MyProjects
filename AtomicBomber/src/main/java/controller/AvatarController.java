package controller;

import javafx.stage.Stage;
import model.User;
import view.StartMain;

public class AvatarController {
    public static void backToProfileMenu() {
        Stage stage = StartMain.getStageStart();
        stage.setScene(User.getCurrentUser().getMainMenuMain().getProfileMain().getSceneProfile());
        stage.show();
    }
}
