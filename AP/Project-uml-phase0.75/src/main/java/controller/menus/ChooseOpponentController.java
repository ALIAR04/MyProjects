package controller.menus;

import java.io.IOException;

import model.User;
import view.PregameFXMenu;

public class ChooseOpponentController {

    public static String startGameWith(String opponentUsername) throws IOException {
        User opponent = User.getUserByUsername(opponentUsername);
        if(opponent == null) {
            return "no such user";
        }
        if(opponentUsername.equals(User.getLoggedInUser().getUsername())) {
            return "you can't play a game with yourself";
        }

        // start game here
        PregameFXController.initializeGame(opponent);
        PregameFXMenu.getInstance().run();
        return "OK";
    }
    
}
