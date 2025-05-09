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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.SecurityQuestions;
import model.User;

public class PickQuestionMenu {
    
private static final PickQuestionMenu INSTANCE = new PickQuestionMenu();

    public static PickQuestionMenu getInstance() {
        return INSTANCE;
    }

    Stage stage;
    Scene scene;
    Scanner scanner;

    private User targetUser;

    public void run(User user) throws IOException {
        stage = Launcher.getStage();
        if(this.scanner == null) {
            scanner = Launcher.getScanner();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/pick-question-menu.fxml"));
        scene = new Scene(loader.load());
        ((PickQuestionMenu) loader.getController()).setUser(user);
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(User user) {
        targetUser = user;
    }
    
    private static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
    
    // ==========================================================

    @FXML
    private ComboBox<SecurityQuestions> questionMenu;

    @FXML
    private TextField answerField;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorLabel;
    

    @FXML
    private void initialize() {
        questionMenu.getItems().add(SecurityQuestions.QUESTION1);
        questionMenu.getItems().add(SecurityQuestions.QUESTION2);
        questionMenu.getItems().add(SecurityQuestions.QUESTION3);
    }

    @FXML
    private void enableRegisterButton() {
        registerButton.setDisable(false);
    }

    @FXML
    private void setQuestion() throws IOException {
        SecurityQuestions selectedQuestion = questionMenu.getValue();
        String answer = answerField.getText();
        if(answer.isEmpty()) {
            errorLabel.setText("please provide an answer");
            return;
        }
        targetUser.setSecurityQuestions(selectedQuestion, false);
        targetUser.setAnswerOfSecurityQuestion(answer, false);
        User.updateUsers();
        LoginMenu.getInstance().run();
    }

}
