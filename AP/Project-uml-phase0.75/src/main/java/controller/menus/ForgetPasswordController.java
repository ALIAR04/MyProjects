package controller.menus;

import controller.Validation;
import model.User;

public class ForgetPasswordController {
    public static String setPassword(User user, String password, String confirm) {
        if (!Validation.isPasswordValid(password)) return "invalid password";
        if (Validation.isPasswordWeak(password)) return "weak password";
        if (!Validation.passwordConfirm(password, confirm)) return "password confirmation doesn't match password";
        user.setPassword(password, true);
        return "OK";
    }

    public static boolean doesUsernameExist(String username) {
        return User.getUserByUsername(username) != null;
    }

    public static boolean isAnswerRight(User user, String answer) {
        return Validation.securityQuestionConfirm(user, answer);
    }
}
