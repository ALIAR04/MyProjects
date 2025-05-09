package controller.menus;

import model.User;
import model.cards.definitionCards.Card;
import model.cards.definitionCards.Cards;
import model.cards.definitionCards.CommanderCard;
import model.game.Deck;
import model.game.Faction;
import model.game.Player;
import view.PreGameMenu;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class PreGameController {
    public static String createGame(Player player1, Player player2, String player2Username, Deck deck1, Deck deck2) {
        User player1ToSelect = User.getLoggedInUser();
        if (player2Username.equals("")) return "Please enter a player";
        if (player2Username.equals(player1ToSelect.getUsername())) return "Don't select yourself";
        User player2ToSelect = User.getUserByUsername(player2Username);
        if (player2ToSelect == null) return "There is no player with this name";
        player1.setUser(player1ToSelect);
        player2.setUser(player2ToSelect);
        deck1 = new Deck();
        deck2 = new Deck();
        player1.setCurrentDeck(deck1);
        player2.setCurrentDeck(deck2);
        loadFactionAndLeader(player1);
        loadFactionAndLeader(player2);
        return "You create game successfully";
    }

    public static void loadFactionAndLeader(Player player) {
        User user = player.getUser();
        Deck lastDeck = user.getLastDeck();
        Deck currentDeck = player.getCurrentDeck();
        if (lastDeck == null) {
            Faction faction = new Faction("Monsters");
            currentDeck.setFaction(faction);
            CommanderCard commanderCard = (CommanderCard) Card.getCardWithName("King Of the Wild Hunt");
            currentDeck.setCommanderCard(commanderCard);
            player.setCommander(commanderCard);
        } else {
            currentDeck.setFaction(lastDeck.getFaction());
            currentDeck.setCommanderCard(lastDeck.getCommanderCard());
        }
    }

    public static void selectFaction(Player player, Deck deck, Faction faction) {
        deck.setFaction(faction);
        if (faction.getName().equals("Northern Realms")) {
            player.setCommander(Cards.SON_OF_MEDAL.getCard());
            deck.setCommanderCard((CommanderCard) Cards.SON_OF_MEDAL.getCard());
        } else if (faction.getName().equals("Monsters")) {
            player.setCommander(Cards.KING_OF_THE_WILD_HUNT.getCard());
            deck.setCommanderCard((CommanderCard) Cards.KING_OF_THE_WILD_HUNT.getCard());
        } else if (faction.getName().equals("Skellige")) {
            player.setCommander(Cards.KING_BRAN.getCard());
            deck.setCommanderCard((CommanderCard) Cards.KING_BRAN.getCard());
        } else if (faction.getName().equals("Scoia'teal")) {
            player.setCommander(Cards.PUREBLOOD_ELF.getCard());
            deck.setCommanderCard((CommanderCard) Cards.PUREBLOOD_ELF.getCard());
        } else if (faction.getName().equals("Empire Nilfgaardian")) {
            player.setCommander(Cards.THE_RELENTLESS.getCard());
            deck.setCommanderCard((CommanderCard) Cards.THE_RELENTLESS.getCard());
        }
    }

    public static void saveDeck(Matcher matcher) {

    }

    public static void loadDeck(Matcher matcher) {

    }

    public static void selectLeader(Player player,Deck deck, CommanderCard commanderCard) {
        deck.setCommanderCard(commanderCard);
        player.setCommander(commanderCard);
    }

    public static void addCardToDeck(Deck deck, Card card) {
        Faction faction = deck.getFaction();
        if (deck.getCards().containsKey(card)) {
            if (faction.availableCards.get(card) > deck.getCards().get(card)) {
                deck.addCardToDeck(card);
            }
        } else {
            deck.addCardToDeck(card);
        }
    }

    public static void deleteCardFromDeck(Deck deck, Card card) {
        if (deck.getCards().containsKey(card)) {
            if (deck.getCards().get(card) > 0) deck.removeCardFromDeck(card);
        }
    }

    public static void changeTurn() {
        PreGameMenu.toggleActive1();
    }

    public static int countAllCards(Player player) {
        int numberOfCardsInDeck = 0;
        for (HashMap.Entry<Card, Integer> set : player.getCurrentDeck().getCards().entrySet()) {
            numberOfCardsInDeck += set.getValue();
        }
        return numberOfCardsInDeck;
    }

    public static int countUnitCards(Player player) {
        int numberOfUnitCards = 0;
        for (Map.Entry<Card, Integer> set : player.getCurrentDeck().getCards().entrySet()) {
            if (set.getKey().isUnit()) numberOfUnitCards += set.getValue();
        }
        return numberOfUnitCards;
    }

    public static int countSpecialCards(Player player) {
        int numberOfSpecialCards = 0;
        for (Map.Entry<Card, Integer> set : player.getCurrentDeck().getCards().entrySet()) {
            if (set.getKey().isSpecial()) numberOfSpecialCards += set.getValue();
        }
        return numberOfSpecialCards;
    }

    public static int countHeroCards(Player player) {
        int numberOfHeroCards = 0;
        for (Map.Entry<Card, Integer> set : player.getCurrentDeck().getCards().entrySet()) {
            if (set.getKey().isHero()) numberOfHeroCards += set.getValue();
        }
        return numberOfHeroCards;
    }

    public static int countTotalStrength(Player player) {
        int totalStrength = 0;
        for (HashMap.Entry<Card, Integer> set : player.getCurrentDeck().getCards().entrySet()) {
            if (set.getKey().isUnit()) totalStrength += set.getKey().getPower();
        }
        return totalStrength;
    }

    public static int countPowerOfCard(Card card) {
        return card.getPower();
    }

    public static String startGame(Player player1, Player player2) {
        Deck deck1 = player1.getCurrentDeck();
        Deck deck2 = player2.getCurrentDeck();
        if (countUnitCards(player1) < 22 || countUnitCards(player2) < 22)
            return "players should select 22 unit cards at least";
        if (countSpecialCards(player1) > 10 || countSpecialCards(player2) > 10)
            return "players can select 10 special cards at most";
        player1.getUser().setLastDeck(deck1, true);
        player2.getUser().setLastDeck(deck2, true);

        //.. start game!!!
        return "";
    }
}
