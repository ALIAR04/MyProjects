package view;

import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.SecurityQuestions;
import model.User;
import model.game.GameResult;
import model.game.Player;
import model.game.Round;
import model.game.RoundList;


public class Launcher extends Application {
    private static Stage mainStage;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Terminal.getTerminal().start(new Stage());
        //createTestUser();
        //multiple calling of the createUser() function will result in multiple instances of the same
        //users in the allUsers list!!!
        //to delete them, go to target/classes/savedObjects/users.json
        //new User() will automatically add the new User to the allUsers list.
        //no need to use User.getAllUsers.add(user) function.
        User.loadUsers();
        User.loadSignedInUser();
        mainStage = stage;
        stage.setTitle("GWENT");
        stage.centerOnScreen();
        if (User.getLoggedInUser() != null)
            MainMenu.getInstance().run();
        else LoginMenu.getInstance().run();
    }
    private void createTestUser() {
        User user = new User("Mahfel", "Hasheminezhad_1", "aa", "a@b.c");
        user.setSecurityQuestions(SecurityQuestions.QUESTION3, false);
        user.setAnswerOfSecurityQuestion("nier", true);
        addTestGame(user);
        User.setLoggedInUser(user);
    }

    private void addTestGame(User user) {
        User opponent = new User("Simulacra", "Hasheminezhad_1", "bb", "a@b.c");
        opponent.setSecurityQuestions(SecurityQuestions.QUESTION1, true);
        opponent.setAnswerOfSecurityQuestion("2B", false); 

        ArrayList<Integer> userScores = new ArrayList<>(List.of(80, 20, 143));
        ArrayList<Integer> opScores = new ArrayList<>(List.of(90, 20, 143));


        ArrayList<Integer> userScores2 = new ArrayList<>(List.of(47, 20));
        ArrayList<Integer> opScores2 = new ArrayList<>(List.of(90, 32));   

        ArrayList<Integer> userScores3 = new ArrayList<>(List.of(81, 20, 90));
        ArrayList<Integer> opScores3 = new ArrayList<>(List.of(80, 32, 89));

        RoundList rounds = createTestRounds(user, opponent, userScores, opScores);
        RoundList rounds2 = createTestRounds(user, opponent, userScores2, opScores2);
        RoundList rounds3 = createTestRounds(user, opponent, userScores3, opScores3);

        User winner = findTestWinner(rounds);
        User winner2 = findTestWinner(rounds2);
        User winner3 = findTestWinner(rounds3);

        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);

        int finalScoreOfPlayer1 = getFinalScoreOfPlayer(userScores);
        int finalScoreOfPlayer2 = getFinalScoreOfPlayer(opScores);
        GameResult game = new GameResult(user, opponent, currentDate, userScores, opScores, finalScoreOfPlayer1, finalScoreOfPlayer2, rounds, winner);
        GameResult gameOpponent = new GameResult(opponent, user, currentDate, userScores, opScores, finalScoreOfPlayer2, finalScoreOfPlayer1, rounds, winner);

        finalScoreOfPlayer1 = getFinalScoreOfPlayer(userScores2);
        finalScoreOfPlayer2 = getFinalScoreOfPlayer(opScores2);
        GameResult game2 = new GameResult(user, opponent, currentDate, userScores2, opScores2, finalScoreOfPlayer1, finalScoreOfPlayer2, rounds, winner2);
        GameResult gameOpponent2 = new GameResult(opponent, user, currentDate, opScores2, userScores2, finalScoreOfPlayer2, finalScoreOfPlayer1, rounds2, winner2);

        finalScoreOfPlayer1 = getFinalScoreOfPlayer(userScores3);
        finalScoreOfPlayer2 = getFinalScoreOfPlayer(opScores3);
        GameResult game3 = new GameResult(user, opponent, currentDate, userScores3, opScores3, finalScoreOfPlayer1, finalScoreOfPlayer2, rounds, winner3);
        GameResult gameOpponent3 = new GameResult(opponent, user, currentDate, opScores3, userScores3, finalScoreOfPlayer2, finalScoreOfPlayer1, rounds3, winner3);

        user.updateGameHistory(game, false);
        user.updateGameHistory(game2, false);
        user.updateGameHistory(game3, false);

        opponent.updateGameHistory(gameOpponent, false);
        opponent.updateGameHistory(gameOpponent2, false);
        opponent.updateGameHistory(gameOpponent3, true);
    }

    private int getFinalScoreOfPlayer(ArrayList<Integer> userScores) {
        int sum = 0;
        for (int i: userScores)
            sum += i;
        return sum;
    }

    private User findTestWinner(RoundList rounds) {
        int roundsWonPlayer1 = 0, roundsWonPlayer2 = 0;
        for (Round round: rounds){
            if (round.getWinner() == null) continue;
            else if (round.getWinner().getUser().getUsername().equals(round.getPlayer1().getUser().getUsername()))
                roundsWonPlayer1++;
            else roundsWonPlayer2++;
        }
        if (roundsWonPlayer1 < roundsWonPlayer2)
            return rounds.get(0).getPlayer2().getUser();
        else if (roundsWonPlayer1 == roundsWonPlayer2)
            return null;
        else return rounds.get(0).getPlayer1().getUser();
    }

    private RoundList createTestRounds(User user, User opponent, ArrayList<Integer> userScores,
                                       ArrayList<Integer> opScores) {
        RoundList rounds = new RoundList();
        for (int i = 0; i < userScores.size(); i++)
            rounds.add(createTestRound(user, opponent, userScores.get(i), opScores.get(i)));
        return rounds;
    }

    private Round createTestRound(User user, User opponent, int userScore, int opScore) {
        Player player1 = new Player(user);
        Player player2 = new Player(opponent);
        Player winner;
        if (userScore < opScore)
            winner = player2;
        else if (userScore == opScore)
            winner = null;
        else winner = player2;
        return new Round(player1, player2, userScore, opScore, winner);
    }

    public static Stage getStage() {
        return mainStage;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void exitGame() {
        Platform.exit();
    }
}