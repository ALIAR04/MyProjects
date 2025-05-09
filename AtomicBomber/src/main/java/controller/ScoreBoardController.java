package controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.User;
import view.StartMain;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ScoreBoardController {
    private static final int width = 400;
    private static final int height = 600;

    public static void showMaxScore() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        setSize(pane);
        ArrayList<User> sortedUsers = sortUsersWithMaxScore();
        createVBoxMaxScore(pane, sortedUsers);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    private static void createVBoxMaxScore(Pane pane, ArrayList<User> users) {
        Label labelUsername = new Label("Username");
        labelUsername.setLayoutX(50);
        labelUsername.setLayoutY(50);
        labelUsername.setStyle("-fx-background-color: lightblue");
        pane.getChildren().add(labelUsername);

        Label labelAccuracy = new Label("MaxScore");
        labelAccuracy.setLayoutX(200);
        labelAccuracy.setLayoutY(50);
        labelAccuracy.setStyle("-fx-background-color: lightgreen");
        pane.getChildren().add(labelAccuracy);

        Label labelWave = new Label("Wave");
        labelWave.setLayoutX(300);
        labelWave.setLayoutY(50);
        labelWave.setStyle("-fx-background-color: lightyellow");
        pane.getChildren().add(labelWave);

        if (users.size() < 10) {
            for (int i = 0; i < users.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(users.get(i).getUsername());
                        label.setLayoutX(50);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    } else if (j ==1) {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(String.valueOf(users.get(i).getMaxScore()));
                        label.setLayoutX(200);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    } else {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(String.valueOf(users.get(i).getMaxWave()));
                        label.setLayoutX(300);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    }
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                HBox hBox = new HBox();
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        Label label = new Label();
                        label.setText(users.get(i).getUsername());
                        hBox.getChildren().add(label);
                    } else if (j ==1) {

                    } else {

                    }
                }
            }
        }

    }

    private static ArrayList<User> sortUsersWithMaxScore() {
        ArrayList<User> sortedUsers = new ArrayList<>();
        for (int i = 0; i < User.getAllUsers().size(); i++) {
            boolean flag = false;
            for (int j = 0; j < sortedUsers.size(); j++) {
                if (User.getAllUsers().get(i).getMaxScore() > sortedUsers.get(j).getMaxScore()) {
                    sortedUsers.add(j, User.getAllUsers().get(i));
                    j++;
                    flag = true;
                    break;
                } else if (User.getAllUsers().get(i).getMaxScore() == sortedUsers.get(j).getMaxScore()) {
                    if (User.getAllUsers().get(i).getMaxWave() > sortedUsers.get(j).getMaxWave()) {
                        sortedUsers.add(j, User.getAllUsers().get(i));
                        j++;
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                sortedUsers.add(User.getAllUsers().get(i));
            }
        }
        return sortedUsers;
    }


    public static void showKills() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        setSize(pane);
        ArrayList<User> sortedUsers = sortUsersWithKills();
        createVBoxKills(pane, sortedUsers);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private static void createVBoxKills(Pane pane, ArrayList<User> users) {
        Label labelUsername = new Label("Username");
        labelUsername.setLayoutX(100);
        labelUsername.setLayoutY(50);
        labelUsername.setStyle("-fx-background-color: lightblue");
        pane.getChildren().add(labelUsername);

        Label labelAccuracy = new Label("Kills");
        labelAccuracy.setLayoutX(250);
        labelAccuracy.setLayoutY(50);
        labelAccuracy.setStyle("-fx-background-color: lightgreen");
        pane.getChildren().add(labelAccuracy);

        if (users.size() < 10) {
            for (int i = 0; i < users.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(users.get(i).getUsername());
                        label.setLayoutX(100);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    } else {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(String.valueOf(users.get(i).getKills()));
                        label.setLayoutX(250);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    }
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(users.get(i).getUsername());
                        label.setLayoutX(100);
                        label.setLayoutY(50 + 50 * i);
                        pane.getChildren().add(label);
                    } else {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(String.valueOf(users.get(i).getKills()));
                        label.setLayoutX(250);
                        label.setLayoutY(50 + 50 * i);
                        pane.getChildren().add(label);
                    }
                }
            }
        }
    }

    private static ArrayList<User> sortUsersWithKills() {
        ArrayList<User> sortedUsers = new ArrayList<>();
        for (int i = 0; i < User.getAllUsers().size(); i++) {
            boolean flag = false;
            for (int j = 0; j < sortedUsers.size(); j++) {
                if (User.getAllUsers().get(i).getKills() > sortedUsers.get(j).getKills()) {
                    sortedUsers.add(j, User.getAllUsers().get(i));
                    j++;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                sortedUsers.add(User.getAllUsers().get(i));
            }
        }
        return sortedUsers;
    }

    public static void showScore() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        setSize(pane);
        ArrayList<User> sortedUsers = sortUsersWithScore();
        createVBoxScore(pane, sortedUsers);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private static void createVBoxScore(Pane pane, ArrayList<User> users) {
        Label labelUsername = new Label("Username");
        labelUsername.setLayoutX(100);
        labelUsername.setLayoutY(50);
        labelUsername.setStyle("-fx-background-color: lightblue");
        pane.getChildren().add(labelUsername);

        Label labelAccuracy = new Label("Score");
        labelAccuracy.setLayoutX(250);
        labelAccuracy.setLayoutY(50);
        labelAccuracy.setStyle("-fx-background-color: lightgreen");
        pane.getChildren().add(labelAccuracy);

        if (users.size() < 10) {
            for (int i = 0; i < users.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(users.get(i).getUsername());
                        label.setLayoutX(100);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    } else {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(String.valueOf(users.get(i).getScore()));
                        label.setLayoutX(250);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    }
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(users.get(i).getUsername());
                        label.setLayoutX(100);
                        label.setLayoutY(50 + 50 * i);
                        pane.getChildren().add(label);
                    } else {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(String.valueOf(users.get(i).getScore()));
                        label.setLayoutX(250);
                        label.setLayoutY(50 + 50 * i);
                        pane.getChildren().add(label);
                    }
                }
            }
        }
    }

    private static ArrayList<User> sortUsersWithScore() {
        ArrayList<User> sortedUsers = new ArrayList<>();
        for (int i = 0; i < User.getAllUsers().size(); i++) {
            boolean flag = false;
            for (int j = 0; j < sortedUsers.size(); j++) {
                if (User.getAllUsers().get(i).getScore() > sortedUsers.get(j).getScore()) {
                    sortedUsers.add(j, User.getAllUsers().get(i));
                    j++;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                sortedUsers.add(User.getAllUsers().get(i));
            }
        }
        return sortedUsers;
    }

    public static void showAccuracy() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        setSize(pane);
        ArrayList<User> sortedUsers = sortUsersWithAccuracy();
        createVBoxAccuracy(pane, sortedUsers);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private static void createVBoxAccuracy(Pane pane, ArrayList<User> users) {
        Label labelUsername = new Label("Username");
        labelUsername.setLayoutX(100);
        labelUsername.setLayoutY(50);
        labelUsername.setStyle("-fx-background-color: lightblue");
        pane.getChildren().add(labelUsername);

        Label labelAccuracy = new Label("Accuracy");
        labelAccuracy.setLayoutX(250);
        labelAccuracy.setLayoutY(50);
        labelAccuracy.setStyle("-fx-background-color: lightgreen");
        pane.getChildren().add(labelAccuracy);
        if (users.size() < 10) {
            for (int i = 0; i < users.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(users.get(i).getUsername());
                        label.setLayoutX(100);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    } else {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(String.valueOf(new DecimalFormat("0.00").format(users.get(i).getAccuracy())) + "%");
                        label.setLayoutX(250);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    }
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(users.get(i).getUsername());
                        label.setLayoutX(100);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    } else {
                        Label label = new Label();
                        label.setFont(new Font(20));
                        label.setText(String.valueOf(new DecimalFormat("00.00").format(users.get(i).getAccuracy())) + "%");
                        label.setLayoutX(250);
                        label.setLayoutY(100 + 50 * i);
                        pane.getChildren().add(label);
                    }
                }
            }
        }
    }

    private static ArrayList<User> sortUsersWithAccuracy() {
        ArrayList<User> sortedUsers = new ArrayList<>();
        for (int i = 0; i < User.getAllUsers().size(); i++) {
            boolean flag = false;
            for (int j = 0; j < sortedUsers.size(); j++) {
                if (User.getAllUsers().get(i).getAccuracy() > sortedUsers.get(j).getAccuracy()) {
                    sortedUsers.add(j, User.getAllUsers().get(i));
                    j++;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                sortedUsers.add(User.getAllUsers().get(i));
            }
        }
        return sortedUsers;
    }

    private static void setSize(Pane pane) {
        pane.setMaxHeight(height);
        pane.setMinHeight(height);
        pane.setMaxWidth(width);
        pane.setMinWidth(width);
    }

    public static void backToMainMenuClicked() {
        Stage stage = StartMain.getStageStart();
        stage.setScene(User.getCurrentUser().getMainMenuMain().getSceneMainMenu());
        stage.show();
    }

}
