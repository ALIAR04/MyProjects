package view;

import controller.menus.PreGameController;
import model.cards.definitionCards.Card;
import model.game.Deck;
import model.game.Faction;
import model.game.Player;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PreGameMenu {
    private static Player player1;
    private static Player player2;
    private static boolean active1 = true;
    private static Deck deck1;
    private static Deck deck2;

    public static void run() {

    }

    public static void toggleActive1() {
        if (active1) active1 = false;
        else active1 = true;
    }

    private static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    private static void showFaction() {
        System.out.println("Monsters");
        System.out.println("Empire Nilfgaardian");
        System.out.println("Northern Realms");
        System.out.println("Scoia'tael");
        System.out.println("Skellige");
    }

    private static void showCards(Faction faction) {
        Player activePlayer;
        if (active1) activePlayer = player1;
        else activePlayer = player2;
        Deck currentDeck = activePlayer.getCurrentDeck();
        for (HashMap.Entry<Card, Integer> set : faction.availableCards.entrySet()) {
            if (currentDeck.getCards().containsKey(set.getKey())) {
                showInformationOfCard(set.getKey(), set.getValue(), currentDeck.getCards().get(set.getKey()));
            } else {
                showInformationOfCard(set.getKey(), set.getValue(), 0);
            }
        }
    }

    private static void showInformationOfCard(Card card, int numberOfCard, int numberOfCardInDeck) {
        Player activePlayer;
        if (active1) activePlayer = player1;
        else activePlayer = player2;
        System.out.println("-------------------------------------");
        System.out.println("Name: " + card.getName());
        boolean isHero = card.isHero();
        boolean isSpecial = card.isSpecial();
        if (isSpecial && isHero) System.out.println("Special Card     Hero");
        else if (isSpecial) System.out.println("Special Card");
        else if (isHero) System.out.println("Unit Card     Hero");
        else System.out.println("Unit Card");
        showPosition(card);
        if (card.hasAbility()) System.out.println("Ability: " + card.getAbility().getName());
        else System.out.println("No Ability");
        System.out.println("Number Of Card In This Faction: " + numberOfCard);
        System.out.println("Number Of Card In Deck: " + numberOfCardInDeck);
        if (card.hasPower()) System.out.println("Power: " + PreGameController.countPowerOfCard(card));
        else System.out.println("No Power");
        System.out.println("-------------------------------------");
    }

    private static void showPosition(Card card) {
        System.out.print("position(s): ");
        if (card.getAcceptableRows().contains(1)) System.out.print("melee ");
        if (card.getAcceptableRows().contains(2)) System.out.print("ranged ");
        if (card.getAcceptableRows().contains(3)) System.out.print("siege ");
        System.out.println();
    }

    private static void showDeck() {
        Player activePlayer;
        if (active1) activePlayer = player1;
        else activePlayer = player2;
        for (HashMap.Entry<Card, Integer> set : activePlayer.getCurrentDeck().getCards().entrySet()) {
            if (set.getValue() > 0) {
                showInformationOfCard(set.getKey(),
                set.getKey().getNumberOfCardInFaction(),
                set.getValue());
            }
        }
    }

    private static void showUserInfo() {
        Player player;
        if (active1) player = player1;
        else player = player2;
        System.out.println("Username: " + player.getUser().getUsername());
        System.out.println("Nickname: " + player.getUser().getNickname());
        System.out.println("Faction: " + player.getCurrentDeck().getFaction().getName());
        System.out.println("Number Of Cards: " + PreGameController.countAllCards(player));
        System.out.println("Number Of Unit Cards: " + PreGameController.countUnitCards(player));
        System.out.println("Number Of Special Cards: " + PreGameController.countSpecialCards(player));
        System.out.println("Number Of Hero Cards: " + PreGameController.countHeroCards(player));
        System.out.println("Total Unit Card Strength: " + PreGameController.countTotalStrength(player));
    }

    private static void showLeaders(Faction faction) {
        for (Card card : faction.availableLeaders) {
            System.out.println("------------------------");
            System.out.println(card.getName());
            System.out.println(card.getAbility().getName());
            System.out.println("------------------------");
        }
    }

}
