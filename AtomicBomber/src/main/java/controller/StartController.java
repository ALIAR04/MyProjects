package controller;

import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Guest;
import model.User;
import view.MainMenuMain;
import view.Wave1;
import view.StartMain;
import view.StartMenu;

public class StartController {
    public static void checkUser(String username, String password) {
        boolean flagUsername = false;
        for (User user: User.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                flagUsername = true;
                if (flagUsername) {
                    if (!user.getPassword().equals(password)) {
                        Alert passwordAlert = new Alert(Alert.AlertType.ERROR);
                        passwordAlert.setContentText("password is incorrect");
                        passwordAlert.setHeaderText("Login Error");
                        StartMenu.printAlert(passwordAlert);
                    } else {
                        User loggedIn = User.getUserByName(username);
                        User.setCurrentUser(loggedIn);
                        if (loggedIn.getMainMenuMain() == null) {
                            MainMenuMain mainMenuMain = new MainMenuMain();
                            try {
                                mainMenuMain.start(StartMain.getStageStart());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            User.getCurrentUser().setMainMenuMain(mainMenuMain);
                            goToMainMenu(User.getCurrentUser().getMainMenuMain());
                        } else {
                            goToMainMenu(User.getCurrentUser().getMainMenuMain());
                        }
                    }
                    return;
                }
            }
        }
        if (!flagUsername) {
            Alert usernameAlert = new Alert(Alert.AlertType.ERROR);
            usernameAlert.setContentText("username not found");
            usernameAlert.setHeaderText("Login Error");
            StartMenu.printAlert(usernameAlert);
        }
    }

    public static void goToMainMenu(MainMenuMain mainMenuMain) {
        Stage stage = StartMain.getStageStart();
        stage.setScene(mainMenuMain.getSceneMainMenu());
        stage.show();

    }

    public static void makeTrackReady() {
        Media media = new Media(StartMain.class.getResource("/Tracks/track.mp3").toString());
        MediaPlayer mediaPlayer1 = new MediaPlayer(media);
        StartMain.setMediaPlayer1(mediaPlayer1);
        mediaPlayer1.play();
        mediaPlayer1.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer1.seek(Duration.ZERO);
            }
        });

    }

    public static void guest() {
        Guest guest = new Guest();
        Wave1 wave1 = new Wave1(guest.getUsername());
        try {
            wave1.start(StartMain.getStageStart());
        } catch (Exception e) {
            e.printStackTrace();
        }
        guest.setWave1(wave1);
    }
}
