package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.menus.RegisterController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RegisterMenu {

    private static final RegisterMenu INSTANCE = new RegisterMenu();

    public static RegisterMenu getInstance() {
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
        scene = new Scene(FXMLLoader.load(getClass().getResource("/ui/fxml/register-menu.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    private static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    // ====================================================

    @FXML
    private Label usernameErrorLabel, passwordErrorLabel, emailErrorLabel, generatePassword;

    @FXML
    private TextField usernameField, nicknameField, emailField, shownPasswordField, shownConfirmField;

    @FXML
    private PasswordField hiddenPasswordField, hiddenConfirmField;

    @FXML
    private Button registerButton, haveAccountButton;

    @FXML
    private ImageView showPassword, hidePassword, showConfirm, hideConfirm;

    private boolean isPasswordShown = false;
    private boolean isConfirmShown = false;


    @FXML
    private void showPassword() {
        shownPasswordField.setText(hiddenPasswordField.getText());
        hiddenPasswordField.setVisible(false);
        shownPasswordField.setVisible(true);

        showPassword.setVisible(false);
        hidePassword.setVisible(true);
        shownPasswordField.requestFocus();
        isPasswordShown = true;
    }

    @FXML
    private void showConfirm() {
        shownConfirmField.setText(hiddenConfirmField.getText());
        hiddenConfirmField.setVisible(false);
        shownConfirmField.setVisible(true);

        showConfirm.setVisible(false);
        hideConfirm.setVisible(true);
        shownConfirmField.requestFocus();
        isConfirmShown = true;
    }

    @FXML
    private void hidePassword() {
        hiddenPasswordField.setText(shownPasswordField.getText());
        shownPasswordField.setVisible(false);
        hiddenPasswordField.setVisible(true);
    
        hidePassword.setVisible(false);
        showPassword.setVisible(true);
        hiddenPasswordField.requestFocus();
        isPasswordShown = false;
    }

    @FXML
    private void hideConfirm() {
        hiddenConfirmField.setText(shownConfirmField.getText());
        shownConfirmField.setVisible(false);
        hiddenConfirmField.setVisible(true);
    
        hideConfirm.setVisible(false);
        showConfirm.setVisible(true);
        hiddenConfirmField.requestFocus();
        isConfirmShown = false;
    }

    @FXML
    private void generateRandomPassword() {
        String randomPassword = RegisterController.generateRandomPassword();
        if(isPasswordShown) shownPasswordField.setText(randomPassword);
        else hiddenPasswordField.setText(randomPassword);
    }

    @FXML
    private void gotoLoginMenu() throws IOException {
        LoginMenu.getInstance().run();
    }

    @FXML
    private void register() throws IOException {
        String username = usernameField.getText();
        String nickname = nicknameField.getText();
        String password = isPasswordShown ? shownPasswordField.getText() : hiddenPasswordField.getText();
        String confirm = isConfirmShown ? shownConfirmField.getText() : hiddenConfirmField.getText();
        String email = emailField.getText();

        String result = RegisterController.register(username, password, confirm, nickname, email);
        if(!result.equals("OK")) {
            showError(result);
        }
    }

    private void showError(String error) {
        clearErrorLabels();
        if(isUsernameError(error)) {
            usernameErrorLabel.setText(error);
        }
        if(isPasswordError(error)) {
            passwordErrorLabel.setText(error);
        }
        if(isEmailError(error)) {
            emailErrorLabel.setText(error);
        }
    }

    private boolean isEmailError(String error) {
        return error.equals("invalid email address");
    }

    private boolean isPasswordError(String error) {
        return error.equals("invalid password") || error.equals("weak password")
                || error.equals("password confirmation doesn't match password");
    }

    private boolean isUsernameError(String error) {
        return error.equals("invalid username") || error.startsWith("username already exists");
    }

    private void clearErrorLabels() {
        usernameErrorLabel.setText("");
        passwordErrorLabel.setText("");
        emailErrorLabel.setText("");
    }

}
