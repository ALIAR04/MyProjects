package model.game;

import java.util.ArrayList;

public class GameHistory extends ArrayList<GameResult> {
    public GameHistory(String gameHistoryString) {
        if (gameHistoryString.equals("GameHistory{}"))
            return;
        gameHistoryString = gameHistoryString.substring(12, gameHistoryString.length() - 1);
        String[] gameResults = gameHistoryString.split("\\|\\|");
        for (String result: gameResults)
            this.add(new GameResult(result));
    }

    public GameHistory() {
        super();
    }

    @Override
    public String toString() {
        StringBuilder historyString = new StringBuilder();
        historyString.append("GameHistory{");
        for (GameResult result : this) {
            if (this.indexOf(result) < this.size() - 1)
                historyString.append(result.toString()).append("||");
            else historyString.append(result.toString());
        }
        historyString.append("}");
        return historyString.toString();
    }
}
