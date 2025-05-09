package view;

import controller.GameController;
import controller.menus.PreGameController;
import model.User;
import model.cards.definitionCards.Card;
import model.cards.definitionCards.Cards;
import model.game.Deck;
import model.game.Player;
import model.game.Row;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu {
    public static void run(Scanner scanner) {

    }

    private static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
