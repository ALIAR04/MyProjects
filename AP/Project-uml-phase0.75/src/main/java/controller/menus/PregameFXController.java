package controller.menus;

import java.io.IOException;
import java.util.*;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import model.User;
import model.cards.definitionCards.Card;
import model.cards.definitionCards.CommanderCard;
import model.game.CardGraphicalizer;
import model.game.Deck;
import model.game.Faction;
import model.game.Player;
import view.GameBoard;
import view.PregameFXMenu;

public class PregameFXController {
    private static Player[] player = new Player[2]; // player[0]: logged in user, player[1]: opponent user
    private static int turn = 0;

    public static void initializeGame(User opponent) {
        player[0] = new Player(User.getLoggedInUser());
        player[1] = new Player(opponent);
        turn = 0;
    }


    public static ArrayList<HBox> getAllCardsOfFaction(String factionName) {
        ArrayList<HBox> result = new ArrayList<>();

        Faction faction = new Faction(factionName);
        HBox cardRow = createRow(4);
        for (Card card : faction.availableCards.keySet()) {
            if(card.getNumberOfCardInFaction() == 0) continue;
            CardGraphicalizer graphicalCard = new CardGraphicalizer(card, 120, 200);
            graphicalCard.getStyleClass().add("card");
            cardRow.getChildren().add(graphicalCard);
            int currentCardsCountInRow = cardRow.getChildren().size();
            if(currentCardsCountInRow == 3) {
                result.add(cardRow);
                cardRow = createRow(4);
            }
        }
        if(cardRow.getChildren().size() > 0) {
            result.add(cardRow);
        }

        return result;
    }

    private static HBox createRow(double spacing) {
        HBox result = new HBox();
        result.setAlignment(Pos.CENTER);
        result.setSpacing(spacing);
        return result;
    }

    public static ArrayList<HBox> getAllLeadersOfFaction(String factionName) {
        ArrayList<HBox> result = new ArrayList<>();

        Faction faction = new Faction(factionName);
        HBox leaderRow = createRow(10);
        for (Card card : faction.availableLeaders) {
            CardGraphicalizer graphicalCard = new CardGraphicalizer(card, 150, 250);
            graphicalCard.getStyleClass().add("leaderCard");
            if(card.equals(player[turn].getCurrentDeck().getCommanderCard())) {
                graphicalCard.setId("selectedLeader");
            }
            leaderRow.getChildren().add(graphicalCard);
            int currentCardsCountInRow = leaderRow.getChildren().size();
            if(currentCardsCountInRow == 3) {
                result.add(leaderRow);
                leaderRow = createRow(10);
            }
        }
        if(leaderRow.getChildren().size() > 0) {
            result.add(leaderRow);
        }

        return result;
    }

    public static Deck whatDeckToLoad() {
        Deck lastDeck = player[turn].getUser().getLastDeck();
        if(lastDeck == null) {
            player[turn].setCurrentDeck(new Deck());
            player[turn].getCurrentDeck().setFaction(new Faction("Monsters"));
            return player[turn].getCurrentDeck();
        }
        else {
            player[turn].setCurrentDeck(lastDeck);
            return lastDeck;
        }
    }

    public static void setLeader(Card card) {
        if(!(card instanceof CommanderCard)) {
            System.out.println("error in setting leader card");
            return;
        }
        player[turn].getCurrentDeck().setCommanderCard((CommanderCard) card);
        player[turn].setCommander(card);
    }

    public static void clearPlayerDeck(String factionName) {
        player[turn].setCurrentDeck(new Deck());
        player[turn].setCommander(null);

        player[turn].getCurrentDeck().setFaction(new Faction(factionName));
    }

    public static int countCardInDeck(Card card) {
        return player[turn].getCurrentDeck().getCardCount(card);
    }

    public static void addCardToDeck(Card card) {
        player[turn].getCurrentDeck().addCardToDeck(card);
    }

    public static void removeCardFromDeck(Card card) {
        player[turn].getCurrentDeck().removeCardFromDeck(card);
    }

    public static int countUnitCards() {
        int result = 0;
        for (Card card : player[turn].getCurrentDeck().getCards().keySet()) {
            if(card.isUnit()) result += player[turn].getCurrentDeck().getCardCount(card);
        }
        return result;
    }
    
    public static int countSpecialCards() {
        int result = 0;
        for (Card card : player[turn].getCurrentDeck().getCards().keySet()) {
            if(card.isSpecial()) result += player[turn].getCurrentDeck().getCardCount(card);
        }
        return result;
    }

    public static void confirmDeck() throws IOException {
        player[turn].getUser().setLastDeck(new Deck(player[turn].getCurrentDeck()), true);
        ArrayList<Card> cardsInDeck = new ArrayList<>();
        for (Card card: player[turn].getCurrentDeck().getCards().keySet()){
            for (int i = 0; i < player[turn].getCurrentDeck().getCards().get(card); i++)
                cardsInDeck.add(card.clone(true));
        }
        player[turn].setCardsInDeck(cardsInDeck);

        if(turn == 0) {
            turn = 1;
            PregameFXMenu.getInstance().run();
        } else {
            player[0].setHand(getRandomHand(player[0]
            .getCardsInDeck()));
            player[1].setHand(getRandomHand(player[1]
                    .getCardsInDeck()));
            GameController game = new GameController();
            GameController.setCurrentGame(game);
            if(player[0].getCurrentDeck().getFaction().getName().equals("Scoia'tael")) {
                game.setPlayer1(player[0]);
                game.setPlayer2(player[1]);
            } else if(player[1].getCurrentDeck().getFaction().getName().equals("Scoia'tael")) {
                game.setPlayer1(player[1]);
                game.setPlayer2(player[0]);
            } else {
                game.setPlayer1(player[0]);
                game.setPlayer2(player[1]);
            }
            player[0].setRows();
            player[1].setRows();
            GameBoard.getInstance().run();
        }
    }

    public static boolean isCommanderSelected() {
        return player[turn].getCurrentDeck().getCommanderCard() != null;
    }

    public static String getCurrentPlayerUsername() {
        return player[turn].getUser().getUsername();
    }
    public static ArrayList<Card> getRandomHand(ArrayList<Card> cardsInDeck) {
        Set<Integer> uniqueIntegers = new HashSet<>();
        Random rand = new Random();
        ArrayList<Card> hand = new ArrayList<>();
        while (uniqueIntegers.size() < 10) {
            int randomInt = rand.nextInt(0, cardsInDeck.size());
            uniqueIntegers.add(randomInt);
        }
        for (int i: uniqueIntegers)
            hand.add(cardsInDeck.get(i));
        return hand;
    }
}
