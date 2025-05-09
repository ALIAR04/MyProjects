package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.menus.ProfileController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.User;

public class ProfileMenu {

    private static ProfileMenu INSTANCE = new ProfileMenu();

    public static ProfileMenu getInstance() {
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

        scene = new Scene(FXMLLoader.load(getClass().getResource("/ui/fxml/profile-menu.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    private static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    // =========================================================

    @FXML
    private HBox accountTab, statsTab, historyTab;
    
    @FXML
    private Label backButton;


    @FXML
    private BorderPane accountTabContent, statsTabContent, historyTabContent;

    private Map<HBox, BorderPane> contentOfTab = new HashMap<>();
    

    @FXML
    private void initialize() {
        accountTab.setId("selected");
        contentOfTab.put(accountTab, accountTabContent);
        contentOfTab.put(statsTab, statsTabContent);
        contentOfTab.put(historyTab, historyTabContent);
        statsTabContent.setVisible(false);
        historyTabContent.setVisible(false);
        initializeAccountTab();
        initializeStatsTab();
        initializeHistoryTab();
    }

    @FXML
    private void selectTab(MouseEvent event) {
        unselectAllTabs();
        HBox target = (HBox) event.getSource();
        target.setId("selected");
        if(target == accountTab) {
            initializeAccountTab();
        } else if(target == statsTab) {
            initializeStatsTab();
        } else {
            initializeHistoryTab();
        }
        contentOfTab.get(target).setVisible(true);
    }

    private void unselectAllTabs() {
        accountTab.setId("");
        statsTab.setId("");
        historyTab.setId("");
        accountTabContent.setVisible(false);
        statsTabContent.setVisible(false);
        historyTabContent.setVisible(false);
    }

    @FXML
    private void gotoMainMenu() throws IOException {
        MainMenu.getInstance().run();
    }


    // ================================================================================ 
    // account tab:
    // ================================================================================ 

    @FXML
    private TextField newUsernameField, newNicknameField, newEmailField,
                        newPasswordShownField, oldPasswordShownField;

    @FXML
    private PasswordField newPasswordHiddenField, oldPasswordHiddenField;

    @FXML
    private Label updateUsernameButton, updateNicknameButton, updateEmailButton,
                    updatePasswordButton, oldPasswordConfirmButton, logoutButton,
                    usernameResultField, nicknameResultField, emailResultField, passwordResultField;

    @FXML
    private HBox oldPasswordBox;

    @FXML
    private ImageView showNewPassword, hideNewPassword, showOldPassword, hideOldPassword;

    private boolean isNewPasswordShown = false;
    private boolean isOldPasswordShown = false;

    private Timeline usernameResultTimeline = new Timeline(new KeyFrame(Duration.millis(2500))),
                        nicknameResultTimeline = new Timeline(new KeyFrame(Duration.millis(2500))),
                        emailResultTimeline = new Timeline(new KeyFrame(Duration.millis(2500))),
                        passwordResultTimeline = new Timeline(new KeyFrame(Duration.millis(2500)));


    private void initializeAccountTab() {
        clearResultFields();
        clearInputFields();
        if(isNewPasswordShown) hideNewPassword();
        if(isOldPasswordShown) hideOldPassword();
        oldPasswordBox.setVisible(false);
    }


    private void clearResultFields() {
        usernameResultField.setText("");
        nicknameResultField.setText("");
        emailResultField.setText("");
        passwordResultField.setText("");
    }

    private void clearInputFields() {
        newUsernameField.setText("");
        newNicknameField.setText("");
        newEmailField.setText("");
        newPasswordHiddenField.setText("");
        newPasswordShownField.setText("");
        oldPasswordHiddenField.setText("");
        oldPasswordShownField.setText("");
    }

    @FXML
    private void showNewPassword() {
        newPasswordShownField.setText(newPasswordHiddenField.getText());
        newPasswordHiddenField.setVisible(false);
        newPasswordShownField.setVisible(true);

        showNewPassword.setVisible(false);
        hideNewPassword.setVisible(true);
        newPasswordShownField.requestFocus();
        isNewPasswordShown = true;
    }
    
    @FXML
    private void hideNewPassword() {
        newPasswordHiddenField.setText(newPasswordShownField.getText());
        newPasswordShownField.setVisible(false);
        newPasswordHiddenField.setVisible(true);
    
        hideNewPassword.setVisible(false);
        showNewPassword.setVisible(true);
        newPasswordHiddenField.requestFocus();
        isNewPasswordShown = false;
    }

    @FXML
    private void showOldPassword() {
        oldPasswordShownField.setText(oldPasswordHiddenField.getText());
        oldPasswordHiddenField.setVisible(false);
        oldPasswordShownField.setVisible(true);

        showOldPassword.setVisible(false);
        hideOldPassword.setVisible(true);
        oldPasswordShownField.requestFocus();
        isOldPasswordShown = true;
    }
    
    @FXML
    private void hideOldPassword() {
        oldPasswordHiddenField.setText(oldPasswordShownField.getText());
        oldPasswordShownField.setVisible(false);
        oldPasswordHiddenField.setVisible(true);
    
        hideOldPassword.setVisible(false);
        showOldPassword.setVisible(true);
        oldPasswordHiddenField.requestFocus();
        isOldPasswordShown = false;
    }


    private void showResult(Label resultLabel, String result, boolean success, Timeline countdown) {
        resultLabel.setText("");
        if(success) {
            resultLabel.setTextFill(new Color(0.30196078431372547d, 1, 0.48627450980392156d, 1));
        } else {
            resultLabel.setTextFill(new Color(1, 0.45098039215686275d, 0.45098039215686275d, 1));
        }
        resultLabel.setText(result);
        countdown.stop();
        countdown.setOnFinished((event) -> {
            resultLabel.setText("");
        });
        countdown.play();
    }

    @FXML
    private void updateUsername() {
        String newUsername = newUsernameField.getText();
        String result = ProfileController.changeUsername(newUsername);
        showResult(usernameResultField, result, result.equals("username updated"), usernameResultTimeline);
        if(result.equals("username updated")) newUsernameField.setText("");
    }
    
    @FXML
    private void updateNickname() {
        String newNickname = newNicknameField.getText();
        String result = ProfileController.changeNickname(newNickname);
        showResult(nicknameResultField, result, result.equals("nickname updated"), nicknameResultTimeline);
        if(result.equals("nickname updated")) newNicknameField.setText("");
    }
    
    @FXML
    private void updateEmail() {
        String newEmail = newEmailField.getText();
        String result = ProfileController.changeEmail(newEmail);
        showResult(emailResultField, result, result.equals("email updated"), emailResultTimeline);
        if(result.equals("email updated")) newEmailField.setText("");
    }


    @FXML
    private void updatePassword() {
        String newPassword = isNewPasswordShown ? newPasswordShownField.getText() : newPasswordHiddenField.getText();
        String result = ProfileController.validateNewPassword(newPassword);
        if(!result.equals("OK")) {
            showResult(passwordResultField, result, false, passwordResultTimeline);
        } else {
            oldPasswordBox.setVisible(true);
        }
    }

    @FXML
    private void confirmOldPassword() {
        String newPassword = isNewPasswordShown ? newPasswordShownField.getText() : newPasswordHiddenField.getText();
        String oldPassword = isOldPasswordShown ? oldPasswordShownField.getText() : oldPasswordHiddenField.getText();

        String result = ProfileController.validateNewPassword(newPassword);
        if(!result.equals("OK")) {
            oldPasswordHiddenField.setText("");
            oldPasswordShownField.setText("");
            if(isOldPasswordShown) hideOldPassword();
            oldPasswordBox.setVisible(false);
            showResult(passwordResultField, result, false, passwordResultTimeline);
        } else {
            result = ProfileController.changePassword(oldPassword, newPassword);
            showResult(passwordResultField, result, result.equals("password updated"), passwordResultTimeline);
            if(result.equals("password updated")) {
                oldPasswordHiddenField.setText("");
                oldPasswordShownField.setText("");
                newPasswordHiddenField.setText("");
                newPasswordShownField.setText("");
                if(isOldPasswordShown) hideOldPassword();
                oldPasswordBox.setVisible(false);
            }
        }
    }


    @FXML
    private void logout() throws IOException {
        ProfileController.logoutUser();
    }

    // ================================================================================ 
    // stats tab:
    // ================================================================================ 

    @FXML
    private Label username, nickname, maxScore, rank, matchCount, winCount,
                    drawCount, loseCount, searchUserErrorField, searchButton;

    @FXML
    private TextField searchField;

    private Timeline searchErrorTimeline = new Timeline(new KeyFrame(Duration.millis(2500)));


    private void initializeStatsTab() {
        ArrayList<String> info = ProfileController.getUserInfo(User.getLoggedInUser().getUsername());
        showUserInfo(info);
        searchField.setText("");
        searchUserErrorField.setText("");
    }

    private void showUserInfo(ArrayList<String> info) {
        if(info == null) return;
        username.setText(info.get(0));
        nickname.setText(info.get(1));
        maxScore.setText(info.get(2));
        rank.setText(info.get(3));
        matchCount.setText(info.get(4));
        winCount.setText(info.get(5));
        drawCount.setText(info.get(6));
        loseCount.setText(info.get(7));
    }

    @FXML
    private void searchUser() {
        String username = searchField.getText();
        ArrayList<String> info = ProfileController.getUserInfo(username);
        if(info == null) {
            showSearchError();
        } else {
            searchUserErrorField.setText("");
            searchField.setText("");
            showUserInfo(info);
        }
    }

    private void showSearchError() {
        searchUserErrorField.setText("no such user");
        searchErrorTimeline.stop();
        searchErrorTimeline.setOnFinished((event) -> {
            searchUserErrorField.setText("");
        });
        searchErrorTimeline.play();
    }


    // ================================================================================ 
    // stats tab:
    // ================================================================================ 

    @FXML
    private TextField recordCountField;

    @FXML
    private Label noMatchErrorLabel;

    @FXML
    private VBox recordContainer;

    private void initializeHistoryTab() {
        recordCountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    recordCountField.setText(newValue.replaceAll("\\D", ""));
                }
            }
        });
        recordCountField.setText("");

        ArrayList<VBox> records = ProfileController.getMatchesUpto(5);
        if(records != null) {
            recordContainer.getChildren().clear();
            noMatchErrorLabel.setVisible(false);
            recordContainer.setVisible(true);
            for (VBox record : records) {
                recordContainer.getChildren().add(record);
            }
        }
    }


    @FXML
    private void getRecords() {
        if(recordCountField.getText().equals("")) return;
        int limit = Integer.parseInt(recordCountField.getText());
        ArrayList<VBox> records = ProfileController.getMatchesUpto(limit);
        if(records != null) {
            recordContainer.getChildren().clear();
            for (VBox record : records) {
                recordContainer.getChildren().addAll(record);
            }
        }
    }
}
