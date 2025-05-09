package model.game;

import model.User;

public class Round {
    private String winnerName;
    private String player1Name;
    private String player2Name;
    private Player player1;
    private Player player2;
    private int player1Score;
    private int player2Score;
    private Player winner;

    public Round(Player player1, Player player2, int player1Score, int player2Score, Player winner) {
        this.player1Name = player1.getUser().getUsername();
        this.player2Name = player2.getUser().getUsername();
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.winner = winner;
        if (winner != null)
            this.winnerName = winner.getUser().getUsername();
        else this.winnerName = "NULL";
    }
    public Round(String roundString) {
        roundString = roundString.substring(6, roundString.length() - 1);
        String[] components = roundString.split(", ");
        player1Name = components[0].split(" score:")[0];
        player1Score = Integer.parseInt(components[0].split(" score:")[1]);
        player2Name = components[1].split(" score:")[0];
        player2Score = Integer.parseInt(components[1].split(" score:")[1]);
        winnerName = components[2].substring("winner:".length());
    }
    @Override
    public String toString() {
        return "Round{" +
                player1Name + " score:" + player1Score + ", " +
                player2Name + " score:" + player2Score +
                ", winner:" + winnerName +
                '}';
    }
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public Player getWinner() {
        return winner;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }
}
