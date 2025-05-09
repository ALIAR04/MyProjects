package model;

import view.MainMenuMain;

import java.util.ArrayList;
import java.util.Collections;


public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private static User currentUser;
    private MainMenuMain mainMenuMain;
    private int maxKills = 0;
    private int maxScore = 0;
    private int maxWave = 0;
    private double accuracy = 0;
    private int kills = 0;
    private int score = 0;
    private int numberOfShoots = 0;
    private int numberOfKills = 0;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        allUsers.add(this);
    }


    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void deleteUser(User loggedIn) {
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).equals(loggedIn)) {
                allUsers.remove(i);
                return;
            }
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getUserByName(String username) {
        for (User user: allUsers) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMainMenuMain(MainMenuMain mainMenuMain) {
        this.mainMenuMain = mainMenuMain;
    }

    public MainMenuMain getMainMenuMain() {
        return mainMenuMain;
    }

    public int getMaxKills() {
        return maxKills;
    }

    public void setMaxKills(int maxKills) {
        this.maxKills = maxKills;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getMaxWave() {
        return maxWave;
    }

    public void setMaxWave(int maxWave) {
        this.maxWave = maxWave;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberOfShoots() {
        return numberOfShoots;
    }

    public void setNumberOfShoots(int numberOfShoots) {
        this.numberOfShoots = numberOfShoots;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void setNumberOfKills(int numberOfKills) {
        this.numberOfKills = numberOfKills;
    }
}
