package model.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.User;

public class GameResultGraphicalizer extends VBox {
    private String player, opponent;
    private int playerFinalScore, opponentFinalScore;
    private ArrayList<Integer> playerRoundScores = new ArrayList<>();
    private ArrayList<Integer> opponentRoundScores = new ArrayList<>();
    private String gameDate;
    private boolean playerWon = false, opponentWon = false;

    public GameResultGraphicalizer(GameResult result) {
        super();
        super.setPadding(new Insets(15, 0, 0, 0));
        super.getStyleClass().add("record");

        player = User.getLoggedInUser().getUsername();
        opponent = result.getRival().getUsername();
        playerFinalScore = result.getFinalScoreOfMainPlayer();
        opponentFinalScore = result.getFinalScoreOfRival();
        playerRoundScores = result.getScoresOfPlayer1();
        opponentRoundScores = result.getScoresOfPlayer2();
        int winner = 0;
        if(result.getWinner() != null) {
            if(result.getWinner().getUsername().equals(player)) winner = 1;
            if(result.getWinner().getUsername().equals(opponent)) winner = -1;
        }
        playerWon = winner > 0;
        opponentWon = winner < 0;
        gameDate = GameResultGraphicalizer.parseDate(result.getDateOfGame());
    }

    private static String parseDate(Date dateOfGame) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return dateFormatter.format(dateOfGame);
    }


    public VBox getRecord() {
        HBox finalResult = createFinalResultHBox();
        HBox playerRoundScores = createRoundScores(true);
        HBox opponentRoundScores = createRoundScores(false);
        HBox date = createDateContainer();

        super.getChildren().addAll(finalResult, playerRoundScores, opponentRoundScores, date);
        return this;
    }


    private HBox createFinalResultHBox() {
        HBox container = new HBox();
        container.setAlignment(Pos.BASELINE_CENTER);
        container.setSpacing(10);
        VBox.setMargin(container, new Insets(0, 0, 10, 0));
        container.setPrefHeight(60);

        Label playerUsernameLabel = getPlayerUsernameLabel();
        Label opponentUsernameLabel = getOpponentUsernameLabel();
        Label playerScoreLabel = getPlayerScoreLabel();
        Label opponentScoreLabel = getOpponentScoreLabel();

        container.getChildren().addAll(playerUsernameLabel, playerScoreLabel, opponentScoreLabel, opponentUsernameLabel);

        return container;
    }

    private Label getOpponentScoreLabel() {
        Label result = new Label(String.valueOf(opponentFinalScore));
        HBox.setMargin(result, new Insets(0, 0, 0, 10));
        result.getStyleClass().add("recordFinalScore");
        if(opponentWon) result.setId("winnerFinalScore");
        else if(!playerWon) result.setId("drawFinalScore");
        else result.setId("loserFinalScore");
        result.setEffect(new Glow(0.3));
        return result;
    }
    
    private Label getPlayerScoreLabel() {
        Label result = new Label(String.valueOf(playerFinalScore));
        HBox.setMargin(result, new Insets(0, 10, 0, 0));
        result.getStyleClass().add("recordFinalScore");
        if(playerWon) result.setId("winnerFinalScore");
        else if(!opponentWon) result.setId("drawFinalScore");
        else result.setId("loserFinalScore");
        result.setEffect(new Glow(0.3));
        return result;
    }
    
    private Label getOpponentUsernameLabel() {
        Label result = new Label(String.valueOf(opponent));
        HBox.setMargin(result, new Insets(0, 0, 0, 5));
        result.getStyleClass().add("recordUsername");
        if(opponentWon) result.setId("winner");
        else if(!playerWon) result.setId("draw");
        else result.setId("loser");
        result.setEffect(new Glow(0.3));
        return result;
    }
    
    private Label getPlayerUsernameLabel() {
        Label result = new Label(String.valueOf(player));
        HBox.setMargin(result, new Insets(0, 5, 0, 0));
        result.getStyleClass().add("recordUsername");
        if(playerWon) result.setId("winner");
        else if(!opponentWon) result.setId("draw");
        else result.setId("loser");
        result.setEffect(new Glow(0.3));
        return result;
    }


    private HBox createRoundScores(boolean forPlayer) {
        HBox container = new HBox();
        container.setAlignment(Pos.BASELINE_LEFT);
        container.setPadding(new Insets(0, 0, 0, 30));
        container.setSpacing(20);
        container.setPrefHeight(40);

        Label whichPlayer = new Label((forPlayer ? player : opponent) + "'s scores:");
        whichPlayer.getStyleClass().add("playerRounds");
        container.getChildren().add(whichPlayer);
        if(forPlayer) {
            for (Integer score : playerRoundScores) {
                Label scoreLabel = new Label(String.valueOf(score));
                scoreLabel.getStyleClass().add("roundScores");
                container.getChildren().add(scoreLabel);
            }
        } else {
            for (Integer score : opponentRoundScores) {
                Label scoreLabel = new Label(String.valueOf(score));
                scoreLabel.getStyleClass().add("roundScores");
                container.getChildren().add(scoreLabel);
            }
        }

        return container;
    }


    private HBox createDateContainer() {
        HBox container = new HBox();
        container.setAlignment(Pos.BOTTOM_RIGHT);
        container.setPadding(new Insets(0, 30, 20, 0));
        container.setPrefHeight(40);
        
        Label dateLabel = new Label("at " + gameDate);
        dateLabel.getStyleClass().add("matchDate");
        container.getChildren().add(dateLabel);

        return container;
    }
}
