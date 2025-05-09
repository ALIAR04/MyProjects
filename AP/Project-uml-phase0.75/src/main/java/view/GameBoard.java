package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.cards.definitionCards.Card;
import model.game.CardGraphicalizer;
import model.game.Player;

public class GameBoard {
    
    private static final GameBoard INSTANCE = new GameBoard();

    public static GameBoard getInstance() {
        return INSTANCE;
    }

    Stage stage;
    Scene scene;
    Scanner scanner;

    GameController controller;

    public void run() throws IOException {
        stage = Launcher.getStage();
        if(scanner == null) {
            scanner = Launcher.getScanner();
        }

        scene = new Scene(FXMLLoader.load(getClass().getResource("/ui/fxml/game-board.fxml")));
        stage.setScene(scene);
        stage.show();   
    }

    // ======================================================

    @FXML
    private HBox handBox,
                 playerSiegeBox, playerRangedBox, playerCloseBox,
                 opponentSiegeBox, opponentRangedBox, opponentCloseBox,
                 playerSiegeHornBox, playerRangedHornBox, playerCloseHornBox,
                 opponentSiegeHornBox, opponentRangedHornBox, opponentCloseHornBox,
                 playerDeckBox, opponentDeckBox,
                 playerCommanderBox, opponentCommanderBox;

    @FXML
    private StackPane playerGraveBox, opponentGraveBox, weatherCardBox;

    @FXML
    private Label playerTotalScoreLabel, opponentTotalScoreLabel,
                  playerSiegeScoreLabel, opponentSiegeScoreLabel,
                  playerRangedScoreLabel, opponentRangedScoreLabel,
                  playerCloseScoreLabel, opponentCloseScoreLabel,
                  playerRemainingDeckCardsLabel, opponentRemainingDeckCardsLabel,
                  playerPassStatusLabel, opponentPassStatusLabel,
                  playerUsernameLabel, opponentUsernameLabel,
                  playerFactionLabel, opponentFactionLabel,
                  playerCardsRemainingLabel, opponentCardsRemainingLabel,
                  passRoundButton,
                  cardDescription, confirmButton, previousCard, nextCard;

    @FXML
    private ImageView playerLife1, playerLife2, opponentLife1, opponentLife2;

    @FXML
    private VBox playerInfoBox, opponentInfoBox, cardInfoBox;

    @FXML
    private BorderPane cardInfoPane;


    private ArrayList<Card> showCards;
    private int currentCardToshowIdx;

    @FXML
    private void initialize() {
        controller = GameController.getCurrentGame();
        Player player = controller.getTurnPlayer();
        // player.decreaseLifeByOne();
        loadCurrentPlayer(player);
        Player opponent = controller.getNotTurnPlayer();
        loadOpponent(opponent);
        // load weather??
    }

    private void loadCurrentPlayer(Player player) {
        loadCardsInBox(player.getHand(), handBox);
        loadCardsInBox(player.getDiscardPile(), playerGraveBox);
        loadCardsInBox(player.getRows().get(0).getCards(), playerCloseBox);
        loadCardsInBox(player.getRows().get(1).getCards(), playerRangedBox);
        loadCardsInBox(player.getRows().get(2).getCards(), playerSiegeBox);
        loadPlayerDeckCards(player);
        loadCommander(playerCommanderBox, player.getCommander());
        loadPlayerPassStatus(player);
        loadPlayerScores(player);
        loadCurrentPlayerInfo(player);
        loadCurrentPlayerHealth(player);
    }
    
    
    private void loadPlayerScores(Player player) {
        int siege = controller.getTotalScoreOfRow(player.getRows().get(2));
        int ranged = controller.getTotalScoreOfRow(player.getRows().get(1));
        int close = controller.getTotalScoreOfRow(player.getRows().get(0));
        int total = controller.getTotalScore(player);
        playerSiegeScoreLabel.setText(String.valueOf(siege));
        playerRangedScoreLabel.setText(String.valueOf(ranged));
        playerCloseScoreLabel.setText(String.valueOf(close));
        playerTotalScoreLabel.setText(String.valueOf(total));
    }

    private void loadPlayerDeckCards(Player player) {
        int cardInDeckCount = player.getCardsInDeck().size();
        playerRemainingDeckCardsLabel.setText(cardInDeckCount + " Cards in deck");
    }

    private void loadPlayerPassStatus(Player player) {
        if(controller.getNameOfPlayer(player).equals(controller.getNameOfPlayer(controller.getPlayer1()))) {
            if(controller.isPlayer1HasPassed()) {
                playerPassStatusLabel.setText("Passed");
                passRoundButton.setText("");
            } else {
                playerPassStatusLabel.setText("");
                passRoundButton.setText("Pass round");
            }
        }
        
        if(controller.getNameOfPlayer(player).equals(controller.getNameOfPlayer(controller.getPlayer2()))) {
            if(controller.isPlayer2HasPassed()) {
                playerPassStatusLabel.setText("Passed");
                passRoundButton.setText("");
            } else {
                playerPassStatusLabel.setText("");
                passRoundButton.setText("Pass round");
            }
        }
    }

    private void loadCurrentPlayerHealth(Player player) {
        if(player.getLife() >= 1) {
            playerLife1.setImage(new Image(getClass().getResource("/ui/icons/full-life.png").toExternalForm()));
        } else {
            playerLife1.setImage(new Image(getClass().getResource("/ui/icons/empty-life.png").toExternalForm()));
        }

        if(player.getLife() >= 2) {
            playerLife2.setImage(new Image(getClass().getResource("/ui/icons/full-life.png").toExternalForm()));
        } else {
            playerLife2.setImage(new Image(getClass().getResource("/ui/icons/empty-life.png").toExternalForm()));
        }
    }
    
    private void loadOpponent(Player opponent) {
        loadCardsInBox(opponent.getDiscardPile(), opponentGraveBox);
        loadCardsInBox(opponent.getRows().get(0).getCards(), opponentCloseBox);
        loadCardsInBox(opponent.getRows().get(1).getCards(), opponentRangedBox);
        loadCardsInBox(opponent.getRows().get(2).getCards(), opponentSiegeBox);
        loadOpponentDeckCards(opponent);
        loadCommander(opponentCommanderBox, opponent.getCommander());
        loadOpponentPassStatus(opponent);
        loadOpponentScores(opponent);
        loadOpponentInfo(opponent);
        loadOpponentPlayerHealth(opponent);
    }
    
    private void loadOpponentScores(Player opponent) {
        int siege = controller.getTotalScoreOfRow(opponent.getRows().get(2));
        int ranged = controller.getTotalScoreOfRow(opponent.getRows().get(1));
        int close = controller.getTotalScoreOfRow(opponent.getRows().get(0));
        int total = controller.getTotalScore(opponent);
        opponentSiegeScoreLabel.setText(String.valueOf(siege));
        opponentRangedScoreLabel.setText(String.valueOf(ranged));
        opponentCloseScoreLabel.setText(String.valueOf(close));
        opponentTotalScoreLabel.setText(String.valueOf(total));
    }

    private void loadOpponentDeckCards(Player opponent) {
        int cardInDeckCount = opponent.getCardsInDeck().size();
        opponentRemainingDeckCardsLabel.setText(cardInDeckCount + " Cards in deck");
    }

    private void loadOpponentPassStatus(Player opponent) {
        if(controller.getNameOfPlayer(opponent).equals(controller.getNameOfPlayer(controller.getPlayer1()))) {
            if(controller.isPlayer1HasPassed()) {
                opponentPassStatusLabel.setText("Passed");
            } else {
                playerPassStatusLabel.setText("");
            }
        }
        
        if(controller.getNameOfPlayer(opponent).equals(controller.getNameOfPlayer(controller.getPlayer2()))) {
            if(controller.isPlayer2HasPassed()) {
                playerPassStatusLabel.setText("Passed");
            } else {
                playerPassStatusLabel.setText("");
            }
        }
    }

    private void loadOpponentPlayerHealth(Player opponent) {
        if(opponent.getLife() >= 1) {
            opponentLife1.setImage(new Image(getClass().getResource("/ui/icons/full-life.png").toExternalForm()));
        } else {
            opponentLife1.setImage(new Image(getClass().getResource("/ui/icons/empty-life.png").toExternalForm()));
        }
    
        if(opponent.getLife() >= 2) {
            opponentLife2.setImage(new Image(getClass().getResource("/ui/icons/full-life.png").toExternalForm()));
        } else {
            opponentLife2.setImage(new Image(getClass().getResource("/ui/icons/empty-life.png").toExternalForm()));
        }
    }

    private void loadCardsInBox(ArrayList<Card> cards, Pane box) {
        for (Card card : cards) {
            CardGraphicalizer graphicalCard = new CardGraphicalizer(card, 60, 90);
            graphicalCard.getStyleClass().add("card");
            addClickHandler(graphicalCard);
            box.getChildren().add(graphicalCard);
        }
    }
    
    private void loadCommander(HBox box, Card commander) {
        CardGraphicalizer card = new CardGraphicalizer(commander, 74, 100);
        card.getStyleClass().add("card");
        addClickHandler(card);
        box.getChildren().add(card);
    }
    
    private void addClickHandler(CardGraphicalizer graphicalCard) {
        graphicalCard.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.SECONDARY) {

                ArrayList<Card> cards = new ArrayList<>();
                cards.add(graphicalCard.getCard());
                showCardsInfo(cards);

            }
            if(event.getButton() == MouseButton.MIDDLE) {

                System.out.println("enter 1 to refill health");
                int response = Launcher.getScanner().nextInt();
                if(response == 1) {
                    refillHealth();
                }

            }
        });
    }


    private void refillHealth() {
        Player player = controller.getTurnPlayer();
        player.setLife(2);
        loadCurrentPlayerHealth(player);
    }

    private void showCardsInfo(ArrayList<Card> cards) {
        confirmButton.setText("Back");
        confirmButton.setOnMouseClicked(event -> {
            cardInfoBox.getChildren().remove(0);
            cardInfoPane.setVisible(false);
        });
        showCards = cards;
        currentCardToshowIdx = showCards.size()-1;
        // showCardInfo(cards, cards.get(cards.size()-1), cards.size()-1);
        showCardInfo();
        cardInfoPane.setVisible(true);
    }

    private void showCardInfo() {
        if(currentCardToshowIdx == 0) previousCard.setDisable(true);
        else previousCard.setDisable(false);

        if(currentCardToshowIdx == showCards.size()-1) nextCard.setDisable(true);
        else nextCard.setDisable(false);

        if(cardInfoBox.getChildren().get(0) instanceof Rectangle) {
            cardInfoBox.getChildren().remove(0);
        }

        CardGraphicalizer graphicalCard = new CardGraphicalizer(showCards.get(currentCardToshowIdx), 240, 453);
        graphicalCard.getStyleClass().add("bigCard");
        
        cardInfoBox.getChildren().add(0, graphicalCard);
        if(showCards.get(currentCardToshowIdx).getAbility() != null) cardDescription.setText(showCards.get(currentCardToshowIdx).getAbility().getDescription());
        else cardDescription.setText("nothing");
    }

    @FXML
    private void showPreviousCard() {
        currentCardToshowIdx--;
        showCardInfo();
    }

    @FXML
    private void showNextCard() {
        currentCardToshowIdx++;
        showCardInfo();
    }

    private void showCardInfo(ArrayList<Card> cards, Card card, int idx) {
        if(idx == 0) previousCard.setDisable(true);
        else previousCard.setDisable(false);
        
        if(idx == cards.size() - 1) nextCard.setDisable(true);
        else nextCard.setDisable(false);
        
        CardGraphicalizer graphicalCard = new CardGraphicalizer(card, 240, 453);
        graphicalCard.getStyleClass().add("bigCard");
        
        cardInfoBox.getChildren().add(0, graphicalCard);
        if(card.getAbility() != null) cardDescription.setText(card.getAbility().getDescription());
        else cardDescription.setText("nothing");
        
        nextCard.setOnMouseClicked(event -> {
            // if(idx == cards.size()-1) return;
            cardInfoBox.getChildren().remove(0);
            showCardInfo(cards, cards.get(idx + 1), idx + 1);
        });
        previousCard.setOnMouseClicked(event -> {
            // if(idx == 0) return;
            cardInfoBox.getChildren().remove(0);
            showCardInfo(cards, cards.get(idx - 1), idx - 1);
        });
    }



    private void loadCurrentPlayerInfo(Player player) {
        setBackgroundOfInfoBox(playerInfoBox, player.getCurrentDeck().getFaction().getName());

        playerUsernameLabel.setText(player.getUser().getUsername());
        playerFactionLabel.setText(player.getCurrentDeck().getFaction().getName());
        playerCardsRemainingLabel.setText(controller.getNumberOfCardsInHand(player) + " cards in hand");

        playerUsernameLabel.setTextFill(GameController.getColorOfFaction(player.getCurrentDeck().getFaction().getName()));
        playerFactionLabel.setTextFill(GameController.getColorOfFaction(player.getCurrentDeck().getFaction().getName()));
        playerCardsRemainingLabel.setTextFill(GameController.getColorOfFaction(player.getCurrentDeck().getFaction().getName()));
    }
    
    private void loadOpponentInfo(Player opponent) {
        setBackgroundOfInfoBox(opponentInfoBox, opponent.getCurrentDeck().getFaction().getName());

        opponentUsernameLabel.setText(opponent.getUser().getUsername());
        opponentFactionLabel.setText(opponent.getCurrentDeck().getFaction().getName());
        opponentCardsRemainingLabel.setText(controller.getNumberOfCardsInHand(opponent) + " cards in hand");

        opponentUsernameLabel.setTextFill(GameController.getColorOfFaction(opponent.getCurrentDeck().getFaction().getName()));
        opponentFactionLabel.setTextFill(GameController.getColorOfFaction(opponent.getCurrentDeck().getFaction().getName()));
        opponentCardsRemainingLabel.setTextFill(GameController.getColorOfFaction(opponent.getCurrentDeck().getFaction().getName()));
    }

    private void setBackgroundOfInfoBox(VBox infoBox, String factionName) {
        infoBox.setId(factionName.toLowerCase().replaceAll(" ", "-").replaceAll("'", "") + "-background");
    }


    @FXML
    private void showCardsInsideBox(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {

            Pane box = (Pane) event.getSource();
            ArrayList<Card> cards = new ArrayList<>();
            for (Node node : box.getChildren()) {
                cards.add(((CardGraphicalizer) node).getCard());
                // System.out.println(((CardGraphicalizer) node).getCard());
            }
            showCardsInfo(cards);

        }
    }
}
