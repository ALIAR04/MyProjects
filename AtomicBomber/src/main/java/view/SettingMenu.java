package view;

import controller.MainMenuController;
import controller.SettingController;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingMenu {
    public Slider difficulty;

    @FXML
    public void initialize() {
        difficulty.setValue(User.getCurrentUser().getMainMenuMain().getDifficulty());
    }

    public void backToMain(MouseEvent mouseEvent) {
        int difficultyToNumber;
        if (difficulty.getValue() < 1.5) difficultyToNumber = 1;
        else if (difficulty.getValue() >= 1.5 && difficulty.getValue() < 2.5) difficultyToNumber = 2;
        else difficultyToNumber = 3;
        SettingController.backToMainMenu(difficultyToNumber);
    }

    public void mute(MouseEvent mouseEvent) {
        SettingController.mute();
    }
}
