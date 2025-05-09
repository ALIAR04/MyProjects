package view;

import controller.ScoreBoardController;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.User;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ScoreBoardMenu {
    private static final int width = 400;
    private static final int height = 600;


    public void showMaxScore(MouseEvent mouseEvent) {
        ScoreBoardController.showMaxScore();
    }


    public void showKills(MouseEvent mouseEvent) {
        ScoreBoardController.showKills();
    }

    public void showScore(MouseEvent mouseEvent) {
        ScoreBoardController.showScore();
    }

    public void showAccuracy(MouseEvent mouseEvent) {
        ScoreBoardController.showAccuracy();
    }

    public void backToMainMenuClicked(MouseEvent mouseEvent) {
        ScoreBoardController.backToMainMenuClicked();
    }
}
