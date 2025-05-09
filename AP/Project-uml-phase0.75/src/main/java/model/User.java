package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controller.UserTypeAdapter;
import controller.menus.RegisterController;
import model.game.*;
import view.Launcher;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private static User loggedInUser;
    private SecurityQuestions securityQuestions;
    private String answerOfSecurityQuestion;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private int numberOfGames = 0;
    private int wins = 0;
    private int draws = 0;
    private int loses = 0;
    private int highScore = 0;
    private int totalScore = 0;
    private DeckList savedDecks = new DeckList();
    private GameHistory gameHistory = new GameHistory();
    private Deck lastDeck;

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        allUsers.add(this);
    }

    public User() {
        allUsers.add(this);
    }

    public void setSavedDecks(ArrayList<Deck> decks, boolean update) {
        this.savedDecks = (DeckList) decks;
        if (update) updateUsers();
    }
    public void setGameHistory(ArrayList<GameResult> gameHistory, boolean update) {
        this.gameHistory = (GameHistory) gameHistory;
        if (update) updateUsers();
    }

    public ArrayList<Deck> getSavedDecks() {
        return savedDecks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username, boolean update) {
        this.username = username;
        if(update) updateUsers();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password, boolean update) {
        this.password = password;
        if(update) updateUsers();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname, boolean update) {
        this.nickname = nickname;
        if (update) updateUsers();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email, boolean update) {
        this.email = email;
        if (update) updateUsers();
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    public SecurityQuestions getSecurityQuestions() {
        return securityQuestions;
    }

    public void setSecurityQuestions(SecurityQuestions securityQuestions, boolean update) {
        this.securityQuestions = securityQuestions;
        if (update) updateUsers();
    }

    public String getAnswerOfSecurityQuestion() {
        return answerOfSecurityQuestion;
    }

    public void setAnswerOfSecurityQuestion(String answerOfSecurityQuestion, boolean update) {
        this.answerOfSecurityQuestion = answerOfSecurityQuestion;
        if (update) updateUsers();
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
        updateUsers();
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames, boolean update) {
        this.numberOfGames = numberOfGames;
        if (update) updateUsers();
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins, boolean update) {
        this.wins = wins;
        if (update) updateUsers();
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws, boolean update) {
        this.draws = draws;
        if (update) updateUsers();
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses, boolean update) {
        this.loses = loses;
        if (update) updateUsers();
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore, boolean update) {
        this.highScore = highScore;
        if (update) updateUsers();
    }

    public ArrayList<Deck> getDecks() {
        return savedDecks;
    }
    public ArrayList<GameResult> getGameHistory() {
        return gameHistory;
    }

    public void addGameHistory(GameResult gameHistories) {
        this.gameHistory.add(gameHistories);
    }

    public Deck getLastDeck() {
        return lastDeck;
    }

    public void setLastDeck(Deck lastDeck, boolean update) {
        this.lastDeck = lastDeck;
        if (update) updateUsers();
    }
    public int getTotalScore(){
        return totalScore;
    }
    public int getRank() {
        int rank = 1;
        for (User user : allUsers) {
            if (user.wins > this.wins) rank++;
        }
        return rank;
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }
    public static void loadUsers() {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(User.class, new UserTypeAdapter())
                    .create();
            String path = String.valueOf(Launcher.class.getResource("/savedObjects/users.json"));
            path = path.substring("file:/".length());
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
            FileReader fileReader = new FileReader(path);
            allUsers = gson.fromJson(fileReader,
                    new TypeToken<ArrayList<User>>() {}.getType());
            if (allUsers == null)
                allUsers = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User user: allUsers){
            for (GameResult result: user.gameHistory){
                User mainPlayer, rival, winner;
                mainPlayer = getUserByUsername(result.getMainPlayerName());
                rival = getUserByUsername(result.getRivalName());
                if (result.getWinnerName().equals(result.getMainPlayerName()))
                    winner = mainPlayer;
                else winner = rival;
                result.setMainPlayer(mainPlayer);
                result.setRival(rival);
                result.setWinner(winner);
            }
        }
    }
    public static void loadSignedInUser() {
        String path = String.valueOf(Launcher.class.getResource("/savedObjects/signedInUser.txt"));
        path = path.substring("file:/".length());
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        try {
            FileReader fileReader = new FileReader(path);
            StringBuilder username = new StringBuilder();
            int charValue;
            while ((charValue = fileReader.read()) != -1) {
                char character = (char) charValue;
                username.append(character);
            }
            loggedInUser = getUserByUsername(username.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveSignedInUser(User user) {
        try {
            String path = String.valueOf(Launcher.class.getResource("/savedObjects/signedInUser.txt"));
            path = path.substring("file:/".length());
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(user.getUsername());
            myWriter.close();
            System.out.println("Signed in user saved");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void removeSavedSignedInUser() {
        String path = String.valueOf(Launcher.class.getResource("/savedObjects/signedInUser.txt"));
        path = path.substring("file:/".length());
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUsers () {
            RegisterController.saveUsers();
        }
    @Override
    public String toString() {
        String username, email, password, nickname;
        username = Objects.requireNonNullElse(this.username, "null");
        email = Objects.requireNonNullElse(this.email, "null");
        password = Objects.requireNonNullElse(this.password, "null");
        nickname = Objects.requireNonNullElse(this.nickname, "null");
        return "User{" + "username=" + username + ", nickname=" + nickname +
                ", email=" + email + ", password=" + password + "}";
    }
    public void updateGameHistory(GameResult game, boolean update) {
        gameHistory.add(game);
        if (game.getFinalScoreOfMainPlayer() > highScore)
            highScore = game.getFinalScoreOfMainPlayer();
        numberOfGames++;
        if (game.getWinnerName().equals(username)) wins++;
        else if (game.getWinnerName().equals("!NULL!")) draws++;
        else loses++;
        totalScore += game.getFinalScoreOfMainPlayer();
        if (update) updateUsers();
    }
    public void setTotalScore(int totalScore, boolean update) {
        this.totalScore = totalScore;
        if (update) updateUsers();
    }
}
