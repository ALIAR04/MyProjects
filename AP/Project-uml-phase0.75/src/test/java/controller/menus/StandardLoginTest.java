package controller.menus;

import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StandardLoginTest {
    @Test
    void givenNonExistentUsername_returnNoSuchUser(){
        String input = "";
        String expected = "no such user";
        String actual = LoginController.login(input, "Password1$", false);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void givenIncorrectPassword_whenUserExists_returnInvalidPassword(){
        User user = new User();
        user.setUsername("user", false);
        user.setPassword("Password1$", false);
        String actual = LoginController.login("user",
                "Password1", false);
        String expected = "invalid password";
        User.getAllUsers().remove(user);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void givenCorrectInfo_returnOk(){
        User user = new User();
        user.setUsername("user", false);
        user.setPassword("Password1$", false);
        String actual = LoginController.login("user",
                "Password1$", false);
        String expected = "OK";
        User.getAllUsers().remove(user);
        Assertions.assertEquals(expected, actual);
    }
}
