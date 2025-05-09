package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenu {

    private static MainMenu INSTANCE = new MainMenu();

    public static MainMenu getInstance() {
        return INSTANCE;
    }

    Stage stage;
    Scene scene;
    Scanner scanner;

    public void run() throws IOException {
        stage = Launcher.getStage();
        if(this.scanner == null) {
            scanner = Launcher.getScanner();
        }
        
        scene = new Scene(FXMLLoader.load(getClass().getResource("/ui/fxml/main-menu.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    private static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    // ========================================================================

    @FXML
    private Label newGame, profile, leaderboards, gwentv, exit;

    @FXML
    private void startGame() throws IOException {
        ChooseOpponentMenu.getInstance().run();
    }

    @FXML
    private void gotoProfileMenu() throws IOException {
        ProfileMenu.getInstance().run();
    }

    @FXML
    private void gotoLeaderboards() {
        System.out.println("leaderboards coming soon...");
    }

    @FXML
    private void gotoTV() {
        System.out.println("tv coming soon...");
    }

    @FXML
    private void exitGame() {
        Launcher.exitGame();
    }

}
