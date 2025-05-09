package controller.menus;

import com.google.gson.GsonBuilder;
import controller.UserTypeAdapter;
import controller.Validation;
import model.User;
import view.Launcher;
import view.PickQuestionMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;

import com.google.gson.Gson;

public class RegisterController {
    public static String register(String username, String password, String passwordConfirm,
                                String nickname, String email) throws IOException {

        if(Validation.usernameExists(username)) {
            return "username already exists. suggestion: " + randomUsername(username);
        }
        
        else if(!Validation.isUsernameValid(username)) {
            return "invalid username";
        }
        
        else if(!Validation.isPasswordValid(password)) {
            return "invalid password";
        }
        
        else if(Validation.isPasswordWeak(password)) {
            return "weak password";
        }
        
        else if(!Validation.passwordConfirm(password, passwordConfirm)) {
            return "password confirmation doesn't match password";
        }
        
        else if(!Validation.isEmailValid(email)) {
            return "invalid email address";
        }
        else {
            User newUser = new User(username, password, nickname, email);
            setQuestionSecurity(newUser);
            return "OK";
        }
    }
    public static void saveUsers() {
        System.out.println("Saving users.json...");
        ArrayList<User> users = User.getAllUsers();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserTypeAdapter()).setPrettyPrinting()
                .create();
        String json = gson.toJson(users);
        try {
            String path = String.valueOf(Launcher.class.getResource("/savedObjects/users.json"));
            path = path.substring("file:/".length());
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error!!!");
            e.printStackTrace();
        }
    }

    private static String randomUsername(String username) {
        SecureRandom rng = new SecureRandom();
        int randomDigitCount = rng.nextInt(2, 4);
        StringBuilder usernameBuilder = new StringBuilder(username + "-");
        for (int i = 0; i < randomDigitCount; i++) {
            usernameBuilder.append(rng.nextInt(0, 10));
        }
        username = usernameBuilder.toString();
        return username;
    }

    private static void setQuestionSecurity(User user) throws IOException {
        PickQuestionMenu.getInstance().run(user);
    }


    public static String generateRandomPassword() {
        SecureRandom secureRandom = new SecureRandom();
        while (true) {
            byte[] bytes = new byte[6];
            secureRandom.nextBytes(bytes);
            String password = Base64.getEncoder().encodeToString(bytes);
            if (Validation.isPasswordValid(password) && !Validation.isPasswordWeak(password)) return password;
        }
    }

}
