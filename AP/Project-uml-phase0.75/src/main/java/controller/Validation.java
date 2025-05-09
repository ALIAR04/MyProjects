package controller;

import model.User;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isUsernameValid(String username) {
        Pattern patternOfUsername = Pattern.compile("[a-zA-Z0-9-]+");
        if (patternOfUsername.matcher(username).matches()) return true;
        return false;
    }

    public static boolean usernameExists(String username) {
        for (User user: User.getAllUsers()) {
            if (user.getUsername().equals(username)) return true;
        }
        return false;
    }

    public static boolean isEmailValid(String email) {
        Pattern patternOfEmail = Pattern.compile("[a-zA-Z0-9.]+@[a-zA-Z]+\\.[a-zA-Z]+");
        if (patternOfEmail.matcher(email).matches()) return true;
        return false;
    }

    public static boolean isPasswordValid(String password) {
        Pattern patternOfPassword = Pattern.compile("[a-zA-Z0-9!@#$%^&*_+=-]+");
        if (patternOfPassword.matcher(password).matches()) return true;
        return false;
    }

    public static boolean isPasswordWeak(String password) {
        Pattern strongPassword = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_+=_]).{8,}");
        if (strongPassword.matcher(password).matches()) return false;
        return true;
    }

    public static boolean passwordConfirm(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    public static boolean securityQuestionConfirm(User user, String answerOfQuestion) {
        if (user.getAnswerOfSecurityQuestion().equals(answerOfQuestion)) return true;
        return false;
    }

}
