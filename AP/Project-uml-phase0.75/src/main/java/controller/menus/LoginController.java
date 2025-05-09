package controller.menus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.UserTypeAdapter;
import model.User;
import view.Launcher;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


public class LoginController {
    public static String login(String username, String password, boolean staySignedIn) {
        User userWantToLogin = User.getUserByUsername(username);
        if (userWantToLogin == null) {
            return "no such user";
        }
        if (isCorrectPassword(password, userWantToLogin)) {
            User.setLoggedInUser(userWantToLogin);
            if (staySignedIn) User.saveSignedInUser(userWantToLogin);
            return "OK";
        } else {
            return "invalid password";
        }
    }
    private static boolean isCorrectPassword(String password, User user) {
        return user.getPassword().equals(password);
    }
}
