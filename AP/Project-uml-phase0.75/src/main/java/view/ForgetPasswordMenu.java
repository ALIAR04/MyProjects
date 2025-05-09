package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.menus.ForgetPasswordController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ForgetPasswordMenu {

    private static final ForgetPasswordMenu INSTANCE = new ForgetPasswordMenu();

    public static ForgetPasswordMenu getInstance() {
        return INSTANCE;
    }

    Stage stage;
    Scene scene;
    Scanner scanner;

    private User targetUser;

    public void run() throws IOException {
        stage = Launcher.getStage();
        if(scanner == null) {
            scanner = Launcher.getScanner();
        }


        scene = new Scene(FXMLLoader.load(getClass().getResource("/ui/fxml/forget-password-menu.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    private static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    // ================================================================

    @FXML
    private VBox passwordBox, questionBox;

    @FXML
    private Label usernameErrorField, passwordErrorField,
                    answerErrorField, securityQuestion;
    
    @FXML
    private TextField usernameField, shownPasswordField, 
                        shownConfirmField, securityAnswerField;

    @FXML
    private PasswordField hiddenPasswordField, hiddenConfirmField;

    @FXML
    private Button restorePasswordButton, usernameConfirmButton,
                    answerConfirmButton, goBackButton;

    @FXML
    private ImageView showPassword, hidePassword, showConfirm, hideConfirm;

    private boolean isPasswordShown = false;
    private boolean isConfirmShown = false;


    @FXML
    public void initialize() {
        questionBox.setVisible(false);
        passwordBox.setVisible(false);
        restorePasswordButton.setVisible(false);
    }


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
    private void showQuestion() {
        targetUser = null;
        clearScreenAfterUsername();
        String username = usernameField.getText();
        if(!ForgetPasswordController.doesUsernameExist(username)) {
            showUsernameError();
            return;
        }
        targetUser = User.getUserByUsername(username);
        securityQuestion.setText(targetUser.getSecurityQuestions().toString());
        questionBox.setVisible(true);
    }

    private void clearScreenAfterUsername() {
        usernameErrorField.setText("");
        answerErrorField.setText("");
        passwordErrorField.setText("");
        securityAnswerField.setText("");
        hiddenPasswordField.setText("");
        shownPasswordField.setText("");
        hiddenConfirmField.setText("");
        shownConfirmField.setText("");
        questionBox.setVisible(false);
        passwordBox.setVisible(false);
        restorePasswordButton.setVisible(false);
        if(isPasswordShown) hidePassword();
        if(isConfirmShown) hideConfirm();
    }
    
    private void showUsernameError() {
        usernameErrorField.setText("no such user");
    }
    

    @FXML
    private void showPasswordField() {
        clearScreenAfterAnswer();
        String answer = securityAnswerField.getText();
        if(!ForgetPasswordController.isAnswerRight(targetUser, answer)) {
            showAnswerError();
            return;
        }
        passwordBox.setVisible(true);
        restorePasswordButton.setVisible(true);
    }
    
    private void clearScreenAfterAnswer() {
        answerErrorField.setText("");
        passwordErrorField.setText("");
        hiddenPasswordField.setText("");
        shownPasswordField.setText("");
        hiddenConfirmField.setText("");
        shownConfirmField.setText("");
        passwordBox.setVisible(false);
        restorePasswordButton.setVisible(false);
        if(isPasswordShown) hidePassword();
        if(isConfirmShown) hideConfirm();
    }

    private void showAnswerError() {
        answerErrorField.setText("wrong answer");
    }


    @FXML
    private void restorePassword() throws IOException {
        String password = isPasswordShown ? shownPasswordField.getText() : hiddenPasswordField.getText();
        String confirm = isConfirmShown ? shownConfirmField.getText() : hiddenConfirmField.getText();
        String result = ForgetPasswordController.setPassword(targetUser, password, confirm);
        if(!result.equals("OK")) {
            passwordErrorField.setText(result);
            return;
        }
        LoginMenu.getInstance().run();
    }


    @FXML
    private void goBackToLoginMenu() throws IOException {
        LoginMenu.getInstance().run();
    }
}