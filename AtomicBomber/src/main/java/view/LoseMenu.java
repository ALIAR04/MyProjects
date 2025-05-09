package view;

import controller.ResultController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

public class LoseMenu {
    @FXML
    private Label accuracy;
    @FXML
    private Label kills;
    @FXML
    private Label lastWave;

    @FXML
    public void initialize() {
        ResultController.showResult(accuracy, kills, lastWave);
    }


    public void backToMainMenu(MouseEvent mouseEvent) {
        Stage stage = StartMain.getStageStart();
        stage.setScene(User.getCurrentUser().getMainMenuMain().getSceneMainMenu());
        stage.show();
    }
}
