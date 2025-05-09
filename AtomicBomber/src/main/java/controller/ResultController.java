package controller;


import javafx.animation.Transition;
import model.InformationOfGame;
import javafx.scene.control.Label;
import model.User;
import view.MainMenuMain;

import java.text.DecimalFormat;


public class ResultController {
    public static void showResult(Label accuracy, Label kills, Label wave) {
        User loggedIn = User.getCurrentUser();
        InformationOfGame informationOfGame = loggedIn.getMainMenuMain().getInformationOfGame();
        showAccuracy(accuracy, informationOfGame);
        showKills(kills, informationOfGame);
        showWave(wave, informationOfGame);
        controlResult(loggedIn, informationOfGame);
        MainMenuMain mainMenuMain = loggedIn.getMainMenuMain();
        closeTheGame(mainMenuMain);
        mainMenuMain.setWave1(null);
        mainMenuMain.setWave2(null);
        mainMenuMain.setWave3(null);
        mainMenuMain.setDifficulty(1);
        mainMenuMain.setInformationOfGame(null);

    }

    private static void closeTheGame(MainMenuMain mainMenuMain) {
        if (mainMenuMain.getWave1() != null) {
            for (Transition animation: mainMenuMain.getWave1().getGame().animations) {
                animation.stop();
            }
        }
        if (mainMenuMain.getWave2() != null) {
            for (Transition animation: mainMenuMain.getWave2().getGame().animations) {
                animation.stop();
            }
        }
        if (mainMenuMain.getWave3() != null) {
            for (Transition animation: mainMenuMain.getWave3().getGame().animations) {
                animation.stop();
            }
        }
    }

    private static void controlResult(User loggedIn, InformationOfGame informationOfGame) {
        if (loggedIn.getMaxKills() < informationOfGame.getKills()) loggedIn.setMaxKills(informationOfGame.getKills());


        if (informationOfGame.getNameOfWave().equals("wave3")) loggedIn.setMaxWave(3);
        else if (informationOfGame.getNameOfWave().equals("wave2") && loggedIn.getMaxWave() < 2) loggedIn.setMaxWave(2);
        else if (informationOfGame.getNameOfWave().equals("wave1") && loggedIn.getMaxWave() < 1) loggedIn.setMaxWave(1);

        if (informationOfGame.getKills() * loggedIn.getMainMenuMain().getDifficulty() > loggedIn.getMaxScore()) loggedIn.setMaxScore(loggedIn.getMainMenuMain().getDifficulty() * informationOfGame.getKills());

        loggedIn.setKills(loggedIn.getKills() + informationOfGame.getKills());
        loggedIn.setScore(loggedIn.getScore() + (informationOfGame.getKills() * loggedIn.getMainMenuMain().getDifficulty()));
        loggedIn.setNumberOfKills(loggedIn.getNumberOfKills() + informationOfGame.getNumberOfKills());
        loggedIn.setNumberOfShoots(loggedIn.getNumberOfShoots() + informationOfGame.getNumberOfShoots());

        double accuracyNumber = ((double) loggedIn.getNumberOfKills()) / ((double) loggedIn.getNumberOfShoots()) * 100;
        loggedIn.setAccuracy(accuracyNumber);
    }

    private static void showWave(Label wave, InformationOfGame informationOfGame) {
        wave.setText("wave: " + informationOfGame.getNameOfWave());
    }

    private static void showKills(Label kills, InformationOfGame informationOfGame) {
        String string = String.valueOf(informationOfGame.getKills());
        kills.setText("kills: " + string);
    }

    private static void showAccuracy(Label accuracy, InformationOfGame informationOfGame) {
        String string;
        if (informationOfGame.getNumberOfShoots() == 0) {
            string = String.valueOf(0.00);
            accuracy.setText("accuracy: " + string);
            return;
        }
        double accuracyNumber = ((double) informationOfGame.getNumberOfKills()) / ((double) informationOfGame.getNumberOfShoots()) * 100;
        string = String.valueOf(new DecimalFormat("0.00").format(accuracyNumber));
        accuracy.setText("accuracy: " + string);
    }

}
