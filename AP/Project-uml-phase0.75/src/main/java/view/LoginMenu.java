package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.menus.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginMenu {

    private static final LoginMenu INSTANCE = new LoginMenu();

    public static LoginMenu getInstance() {
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
        scene = new Scene(FXMLLoader.load(getClass().getResource("/ui/fxml/login-menu.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    
    private static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    // ==================================================================

    @FXML
    private Label usernameErrorLabel, passwordErrorLabel;

    @FXML
    private TextField usernameField, shownPasswordField;

    @FXML
    private PasswordField hiddenPasswordField;

    @FXML
    private ImageView showPasswordButton, hidePasswordButton;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private Button enterButton, forgotPasswordButton, dontHaveAccountButton;

    private boolean isPasswordShown = false;


    @FXML
    private void showPassword() {
        shownPasswordField.setText(hiddenPasswordField.getText());
        hiddenPasswordField.setVisible(false);
        shownPasswordField.setVisible(true);

        showPasswordButton.setVisible(false);
        hidePasswordButton.setVisible(true);
        shownPasswordField.requestFocus();
        isPasswordShown = true;
    }
    
    @FXML
    private void hidePassword() {
        hiddenPasswordField.setText(shownPasswordField.getText());
        shownPasswordField.setVisible(false);
        hiddenPasswordField.setVisible(true);
    
        hidePasswordButton.setVisible(false);
        showPasswordButton.setVisible(true);
        hiddenPasswordField.requestFocus();
        isPasswordShown = false;
    }

    @FXML
    private void login() throws IOException {
        String username = usernameField.getText();
        String password = (isPasswordShown ? shownPasswordField.getText() : hiddenPasswordField.getText());
        boolean remember = rememberMeCheckBox.isSelected();
        String result = LoginController.login(username, password, remember);
        if(result.equals("OK")) gotoMainMenu();
        showError(result);
    }

    private void showError(String error) {
        usernameErrorLabel.setText("");
        passwordErrorLabel.setText("");
        if(error.equals("no such user")) {
            usernameErrorLabel.setText(error);
        } else {
            passwordErrorLabel.setText(error);
        }
    }


    @FXML
    private void gotoForgetPasswordMenu() throws IOException {
        ForgetPasswordMenu.getInstance().run();
    }

    @FXML
    private void gotoRegisterMenu() throws IOException {
        RegisterMenu.getInstance().run();
    }

    private void gotoMainMenu() throws IOException {
        MainMenu.getInstance().run();
    }
}
