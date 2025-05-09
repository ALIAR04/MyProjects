package model.game;

import model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameResult {
    private String mainPlayerName;
    private String rivalName;
    private User mainPlayer;
    private User rival;
    private String winnerName;
    private Date dateOfGame;
    private ArrayList<Integer> scoresOfPlayer1;
    private ArrayList<Integer> scoresOfPlayer2;
    private int finalScoreOfMainPlayer;
    private int finalScoreOfRival;
    private RoundList rounds = new RoundList();
    private User winner;

    public GameResult(User player1, User player2, Date dateOfGame, ArrayList<Integer> scoresOfPlayer1, ArrayList<Integer> scoresOfPlayer2,
                      int finalScoreOfPlayer1, int finalScoreOfPlayer2, ArrayList<Round> rounds,
                      User winner) {
        this.mainPlayer = player1;
        this.rival = player2;
        this.mainPlayerName = player1.getUsername();
        this.rivalName = player2.getUsername();
        this.winner = winner;
        if (winner != null)
            this.winnerName = winner.getUsername();
        else this.winnerName = "!NULL!";
        this.dateOfGame = dateOfGame;
        this.scoresOfPlayer1 = scoresOfPlayer1; //main player's scores
        this.scoresOfPlayer2 = scoresOfPlayer2; //rival's scores
        this.finalScoreOfMainPlayer = finalScoreOfPlayer1;
        this.finalScoreOfRival = finalScoreOfPlayer2;
        this.rounds.addAll(rounds);
    }
    public GameResult(String resultString){
        resultString = resultString.substring(11, resultString.length() - 1);
        String[] components = resultString.split("---");
        mainPlayerName = components[0].substring("main player:".length());
        rivalName = components[1].substring("rival:".length());
        winnerName = components[2].substring("winner:".length());
        String dateString = components[3].substring("dateOfGame:".length());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateOfGame = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String playerScoresString = components[4].substring("scoresOfPlayer1:".length());
        scoresOfPlayer1 = getScoresOfPlayer(playerScoresString);
        playerScoresString = components[5].substring("scoresOfPlayer2:".length());
        scoresOfPlayer2 = getScoresOfPlayer(playerScoresString);
        finalScoreOfMainPlayer = Integer.parseInt(components[6]
                .substring("finalScoreOfMainPlayer:".length()));
        finalScoreOfRival = Integer.parseInt(components[7].substring("finalScoreOfRival:".length()));
        rounds = new RoundList(components[8].substring("rounds:".length()));
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "GameResult{" +
                "main player:" + mainPlayerName +
                "---rival:" + rivalName +
                "---winner:" + winnerName +
                "---dateOfGame:" +  dateFormat.format(dateOfGame)+
                "---scoresOfPlayer1:" + scoresOfPlayer1.toString() +
                "---scoresOfPlayer2:" + scoresOfPlayer2.toString() +
                "---finalScoreOfMainPlayer:" + finalScoreOfMainPlayer +
                "---finalScoreOfRival:" + finalScoreOfRival +
                "---rounds:" + rounds.toString() +
                '}';
    }
    public User getRival() {
        return rival;
    }

    public Date getDateOfGame() {
        return dateOfGame;
    }

    public ArrayList<Integer> getScoresOfPlayer1() {
        return scoresOfPlayer1;
    }
    private ArrayList<Integer> getScoresOfPlayer(String playerScoresString){
        playerScoresString = playerScoresString.substring(1, playerScoresString.length() - 1);
        String[] scores = playerScoresString.split(", ");
        ArrayList<Integer> playerScores = new ArrayList<>();
        for (String score: scores)
            playerScores.add(Integer.parseInt(score));
        return playerScores;
    }

    public ArrayList<Integer> getScoresOfPlayer2() {
        return scoresOfPlayer2;
    }

    public int getFinalScoreOfMainPlayer() {
        return finalScoreOfMainPlayer;
    }

    public int getFinalScoreOfRival() {
        return finalScoreOfRival;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public String getMainPlayerName() {
        return mainPlayerName;
    }

    public void setMainPlayer(User mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public void setRival(User rival) {
        this.rival = rival;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getWinner() {
        return winner;
    }

    public String getRivalName() {
        return rivalName;
    }

    public String getWinnerName() {
        return winnerName;
    }
}
