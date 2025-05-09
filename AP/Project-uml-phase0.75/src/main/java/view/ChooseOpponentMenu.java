package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.menus.ChooseOpponentController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChooseOpponentMenu {
    
    private static ChooseOpponentMenu INSTANCE = new ChooseOpponentMenu();

    public static ChooseOpponentMenu getInstance() {
        return INSTANCE;
    }

    Stage stage;
    Scene scene;
    Scanner scanner;

    public void run() throws IOException {
        stage = Launcher.getStage();
        if(scanner == null) {
            scanner = Launcher.getScanner();
        }

        scene = new Scene(FXMLLoader.load(getClass().getResource("/ui/fxml/choose-opponent-menu.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    public static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    // ============================================================

    @FXML
    private Label startButton, errorField;

    @FXML
    private TextField opponentField;

    private Timeline errorTimeline = new Timeline(new KeyFrame(Duration.millis(2500)));

    @FXML
    private void startGame() throws IOException {
        String opponent = opponentField.getText();
        String result = ChooseOpponentController.startGameWith(opponent);
        if(!result.equals("OK")) {
            showError(result);
        }
    }

    private void showError(String result) {
        errorTimeline.stop();
        errorTimeline.setOnFinished((event) -> {
            errorField.setText("");
        });
        errorField.setText(result);
        errorTimeline.play();
    }
}
