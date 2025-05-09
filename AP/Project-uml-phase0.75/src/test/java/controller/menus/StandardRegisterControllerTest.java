package controller.menus;

import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StandardRegisterControllerTest {
    @Test
    void givenExistentUsername_returnUsernameExists(){
        User user = new User();
        user.setUsername("user", false);
        user.setPassword("Password1$", false);
        user.setNickname("", false);
        user.setEmail("a@gmail.com", false);
        try {
            String result = RegisterController.register("user", "Password1$",
                    "Password1$", "", "a@gmail.com");
            Matcher matcher = Pattern.compile
                    ("username already exists\\. suggestion: [a-zA-Z0-9-]+$")
                    .matcher(result);
            Assertions.assertTrue(matcher.find());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User.getAllUsers().remove(user);
    }
    @Test
    void givenInvalidUsername_whereUsernameHasSpecialChars_returnInvalidUsername(){
        try {
            String result = RegisterController.register("us!!_&&er", "Password1$",
                    "Password1$", "", "a@gmail.com");
            String expected = "invalid username";
            Assertions.assertEquals(expected, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void givenWeakPassword_returnWeakPassword(){
        try {
            String result = RegisterController.register("user", "pas",
                    "Password1$", "", "a@gmail.com");
            String expected = "weak password";
            Assertions.assertEquals(expected, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void givenPasswordConfirmation_returnPassConfirmationNotMatch(){
        try {
            String result = RegisterController.register("user", "Password1$",
                    "Password1", "", "a@gmail.com");
            String expected = "password confirmation doesn't match password";
            Assertions.assertEquals(expected, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void givenInvalidEmail_returnInvalidEmail(){
        try {
            String result = RegisterController.register("user", "Password1$",
                    "Password1$", "", "a.com");
            String expected = "invalid email address";
            Assertions.assertEquals(expected, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
