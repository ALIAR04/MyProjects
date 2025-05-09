package controller.menus;

import controller.Validation;
import javafx.scene.layout.VBox;
import model.User;
import model.game.GameResult;
import model.game.GameResultGraphicalizer;
import view.LoginMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileController {
    public static String changeUsername(String username) {
        User loggedInUser = User.getLoggedInUser();
        if (!loggedInUser.getUsername().equals(username)) {
            if (Validation.isUsernameValid(username)) {
                if (Validation.usernameExists(username)) {
                    return "username already exists";
                } else {
                    loggedInUser.setUsername(username, true);
                    return "username updated";
                }
            } else {
                return "invalid username";
            }
        } else {
            return "new username is the same as old one";
        }
    }

    public static String validateNewPassword(String newPassword) {
        if(!Validation.isPasswordValid(newPassword)) return "invalid password";
        if(Validation.isPasswordWeak(newPassword)) return "weak password";
        return "OK";
    }

    public static String changePassword(String oldPassword, String newPassword) {
        User loggedInUser = User.getLoggedInUser();
        if (!loggedInUser.getPassword().equals(oldPassword)) {
            return "wrong password";
        } else {
            if (oldPassword.equals(newPassword)) {
                return "new password is the same as old one";
            } else {
                loggedInUser.setPassword(newPassword, true);
                return "password updated";
            }
        }
    }

    public static String changeEmail(String email) {
        User loggedInUser = User.getLoggedInUser();
        if (!loggedInUser.getEmail().equals(email)) {
            if (!Validation.isEmailValid(email)) {
                return "invalid email";
            } else {
                loggedInUser.setEmail(email, true);
                return "email updated";
            }
        } else {
            return "new email is the same as old one";
        }
    }

    public static String changeNickname(String nickname) {
        User loggedInUser = User.getLoggedInUser();
        if (loggedInUser.getNickname().equals(nickname)) {
            return "new nickname is the same as old one";
        } else {
            loggedInUser.setNickname(nickname, true);
            return "nickname updated";
        }
    }

    public static ArrayList<String> getUserInfo(String targetUsername) {
        User user = User.getUserByUsername(targetUsername);
        if(user == null) return null;
        String username = user.getUsername();
        String nickname = user.getNickname();
        String highScore = String.valueOf(user.getHighScore());
        String rank = String.valueOf(user.getRank());
        String numberOfGames = String.valueOf(user.getNumberOfGames());
        String numberOfWins = String.valueOf(user.getWins());
        String numberOfDraws = String.valueOf(user.getDraws());
        String numberOfLoses = String.valueOf(user.getLoses());
        return new ArrayList<>(List.of(username, nickname, highScore, rank, numberOfGames,
                                        numberOfWins, numberOfDraws, numberOfLoses));
    }

    
    public static GameResultGraphicalizer getMatchRecord() {
        User loggedInUser = User.getLoggedInUser();
        GameResultGraphicalizer result = new GameResultGraphicalizer(loggedInUser.getGameHistory().get(0));
        return result;
    }

    public static void logoutUser() throws IOException {
        User.setLoggedInUser(null);
        User.removeSavedSignedInUser();
        LoginMenu.getInstance().run();
    }

    public static ArrayList<VBox> getMatchesUpto(int limit) {
        User loggedInUser = User.getLoggedInUser();
        ArrayList<GameResult> history = loggedInUser.getGameHistory();
        if(history.isEmpty()) return null;
        ArrayList<VBox> result = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            int index = history.size() - i - 1;
            if(index < 0) break;
            result.add(new GameResultGraphicalizer(history.get(index)).getRecord());
        }
        return result;
    }
}
