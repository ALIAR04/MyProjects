package view;

import controller.ResultController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

public class WinMenu {
    @FXML
    private Label lastWave;

    @FXML
    private Label numberOfKills;

    @FXML
    private Label accuracy;

    @FXML
    public void initialize() {
        ResultController.showResult(accuracy, numberOfKills, lastWave);
    }

    public Label getLastWave() {
        return lastWave;
    }

    public void setLastWave(Label lastWave) {
        this.lastWave = lastWave;
    }

    public Label getNumberOfKills() {
        return numberOfKills;
    }

    public void setNumberOfKills(Label numberOfKills) {
        this.numberOfKills = numberOfKills;
    }

    public Label getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Label accuracy) {
        this.accuracy = accuracy;
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        Stage stage = StartMain.getStageStart();
        stage.setScene(User.getCurrentUser().getMainMenuMain().getSceneMainMenu());
        stage.show();
    }
}
