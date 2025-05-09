package controller;

import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.*;
import view.*;

import java.text.DecimalFormat;


public class WaveController {

    public static void setSize(Pane pane) {
        pane.setMaxHeight(Wave1.height);
        pane.setMinHeight(Wave1.height);
        pane.setMaxWidth(Wave1.width);
        pane.setMinWidth(Wave1.width);
    }

    public static BackgroundImage createBackgroundImage() {
        Image image = new Image(WaveController.class.getResource("/Images/background.png").toExternalForm(),
                Wave1.width,
                Wave1.height,
                false,
                false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    public static Jet createJet(Game game, Jet jet, Pane pane) {
        jet = new Jet(game);
        JetAnimation jetAnimation = new JetAnimation(pane, game, jet);
        jet.setJetAnimation(jetAnimation);
        game.animations.add(jetAnimation);
        jetAnimation.play();
        Jet finalJet = jet;
        jet.setOnKeyPressed(keyEvent -> {
            System.out.println(jetAnimation.isWave2Done);
            if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                jetAnimation.rightRelease = true;
            } else if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
                jetAnimation.leftRelease = true;
            } else if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) {
                jetAnimation.upRelease = true;
            } else if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S) {
                jetAnimation.downRelease = true;
            } else if (keyEvent.getCode() == KeyCode.SPACE) {
                jetAnimation.shootRelease = true;
            } else if (keyEvent.getCode() == KeyCode.T) {
                createTank(game, pane, true, finalJet, 1);
            } else if (keyEvent.getCode() == KeyCode.C) {
                shootCluster(finalJet, pane, game);
            } else if (keyEvent.getCode() == KeyCode.DIGIT1) {
                if (User.getCurrentUser().getMainMenuMain().getInformationOfGame().getKills() >= 30 && !jetAnimation.isWave1Done) {
                    jetAnimation.isWave1Done = true;
                    Wave2 wave2 = new Wave2(User.getCurrentUser().getUsername());
                    User.getCurrentUser().getMainMenuMain().setWave2(wave2);
                    jetAnimation.setPane(wave2.getPane());
                    jetAnimation.setGame(wave2.getGame());
                    try {
                        wave2.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (keyEvent.getCode() == KeyCode.DIGIT2) {
                if (User.getCurrentUser().getMainMenuMain().getInformationOfGame().getKills() >= 70 && !jetAnimation.isWave2Done) {
                    System.out.println("in if for wave2 to wave3");
                    jetAnimation.isWave2Done = true;
                    Wave3 wave3 = new Wave3(User.getCurrentUser().getUsername());
                    User.getCurrentUser().getMainMenuMain().setWave3(wave3);
                    jetAnimation.setPane(wave3.getPane());
                    jetAnimation.setGame(wave3.getGame());
                    try {
                        wave3.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (keyEvent.getCode() == KeyCode.DIGIT3) {
                if (User.getCurrentUser().getMainMenuMain().getInformationOfGame().getKills() >= 120) {
                    WinWindowMain winWindowMain = new WinWindowMain(pane, User.getCurrentUser().getMainMenuMain().getInformationOfGame());
                    try {
                        winWindowMain.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (keyEvent.getCode() == KeyCode.H) {
                InformationOfGame informationOfGame = User.getCurrentUser().getMainMenuMain().getInformationOfGame();
                if (informationOfGame.getHitPoint() == 2) {
                    hitPointCheat2(informationOfGame);
                } else if (informationOfGame.getHitPoint() == 1) {
                    hitPointCheat1(informationOfGame);
                }
            } else if (keyEvent.getCode() == KeyCode.CONTROL) {
                InformationOfGame informationOfGame = User.getCurrentUser().getMainMenuMain().getInformationOfGame();
                clusterCheat(informationOfGame);
            } else if (keyEvent.getCode() == KeyCode.P) {
                if (User.getCurrentUser().getMainMenuMain().getWave2() == null) {
                    jetAnimation.isWave1Done = true;
                    Wave2 wave2 = new Wave2(User.getCurrentUser().getUsername());
                    User.getCurrentUser().getMainMenuMain().setWave2(wave2);
                    jetAnimation.setPane(wave2.getPane());
                    jetAnimation.setGame(wave2.getGame());
                    try {
                        wave2.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (User.getCurrentUser().getMainMenuMain().getWave3() == null) {
                    jetAnimation.isWave2Done = true;
                    Wave3 wave3 = new Wave3(User.getCurrentUser().getUsername());
                    User.getCurrentUser().getMainMenuMain().setWave3(wave3);
                    jetAnimation.setPane(wave3.getPane());
                    jetAnimation.setGame(wave3.getGame());
                    try {
                        wave3.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        jet.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                jetAnimation.rightRelease = false;
            } else if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
                jetAnimation.leftRelease = false;
            } else if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) {
                jetAnimation.upRelease = false;
            } else if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S) {
                jetAnimation.downRelease = false;
            } else if (keyEvent.getCode() == KeyCode.SPACE) {
                jetAnimation.shootRelease = false;
            } else if (keyEvent.getCode() == KeyCode.DIGIT1) {
                if (User.getCurrentUser().getMainMenuMain().getInformationOfGame().getKills() >= 20 && !jetAnimation.isWave1Done) {
                    jetAnimation.isWave1Done = true;
                }
            } else if (keyEvent.getCode() == KeyCode.DIGIT2) {
                if (User.getCurrentUser().getMainMenuMain().getInformationOfGame().getKills() >= 50 && !jetAnimation.isWave2Done) {
                    jetAnimation.isWave2Done = true;
                }
            } else if (keyEvent.getCode() == KeyCode.DIGIT3) {
                if (User.getCurrentUser().getMainMenuMain().getInformationOfGame().getKills() >= 100 && !jetAnimation.isWave3Done) {
                    jetAnimation.isWave3Done = true;
                }
            }

        });
        return finalJet;
    }

    private static void clusterCheat(InformationOfGame informationOfGame) {
        informationOfGame.setNumberOfClusters(informationOfGame.getNumberOfClusters() + 1);
        informationOfGame.getLabelClusters().setText(String.valueOf(informationOfGame.getNumberOfClusters()));
    }

    private static void hitPointCheat1(InformationOfGame informationOfGame) {
        informationOfGame.setHitPoint(2);
        if (User.getCurrentUser().getMainMenuMain().getWave3() != null) {
            Pane pane = User.getCurrentUser().getMainMenuMain().getWave3().getPane();
            pane.getChildren().add(informationOfGame.createHitPoint2(pane, User.getCurrentUser()));
        } else if (User.getCurrentUser().getMainMenuMain().getWave2() != null) {
            Pane pane = User.getCurrentUser().getMainMenuMain().getWave2().getPane();
            pane.getChildren().add(informationOfGame.createHitPoint2(pane, User.getCurrentUser()));
        } else {
            Pane pane = User.getCurrentUser().getMainMenuMain().getWave1().getPane();
            pane.getChildren().add(informationOfGame.createHitPoint2(pane, User.getCurrentUser()));
        }
    }

    private static void hitPointCheat2(InformationOfGame informationOfGame) {
        informationOfGame.setHitPoint(3);
        if (User.getCurrentUser().getMainMenuMain().getWave3() != null) {
            Pane pane = User.getCurrentUser().getMainMenuMain().getWave3().getPane();
            pane.getChildren().add(informationOfGame.createHitPoint1(pane, User.getCurrentUser()));
        } else if (User.getCurrentUser().getMainMenuMain().getWave2() != null) {
            Pane pane = User.getCurrentUser().getMainMenuMain().getWave2().getPane();
            pane.getChildren().add(informationOfGame.createHitPoint1(pane, User.getCurrentUser()));
        } else {
            Pane pane = User.getCurrentUser().getMainMenuMain().getWave1().getPane();
            pane.getChildren().add(informationOfGame.createHitPoint1(pane, User.getCurrentUser()));
        }
    }

    private static void shootCluster(Jet jet, Pane pane, Game game) {
        InformationOfGame informationOfGame = User.getCurrentUser().getMainMenuMain().getInformationOfGame();
        if (informationOfGame.getNumberOfClusters() > 0) {
            for (int i = 60; i <= 120; i += 15) {
                informationOfGame.setNumberOfShoots(informationOfGame.getNumberOfShoots() + 1);
                Rocket rocket = new Rocket(jet);
                int indexOfJet = pane.getChildren().indexOf(jet);
                pane.getChildren().add(indexOfJet, rocket);
                RocketAnimation rocketAnimation = new RocketAnimation(pane, game, rocket, i);
                game.animations.add(rocketAnimation);
                rocketAnimation.play();
            }
            informationOfGame.setNumberOfClusters(informationOfGame.getNumberOfClusters() - 1);
            informationOfGame.getLabelClusters().setText(String.valueOf(informationOfGame.getNumberOfClusters()));
        }
    }

    public static void createTank(Game game, Pane pane, boolean flag, Jet jet, int number) {
        for (int i = 0; i < number; i++) {
            Tank tank = new Tank(game);
            tank.setTankAnimation(new TankAnimation(game, pane, tank, flag, jet));
            game.animations.add(tank.getTankAnimation());
            tank.getTankAnimation().play();
            game.tanks.getChildren().add(tank);
        }
    }

    public static void createTruck(Game game, Pane pane, int number) {
        for (int i = 0; i < number; i++) {
            Truck truck = new Truck(game);
            truck.setTruckAnimation(new TruckAnimation(game, pane, truck));
            game.animations.add(truck.getTruckAnimation());
            truck.getTruckAnimation().play();
            game.trucks.getChildren().add(truck);
        }
    }
    public static void createMig(Game game, Pane pane, Jet jet) {
        Mig mig = new Mig(game);
        mig.setMigAnimation(new MigAnimation(pane, game, jet, mig));
        game.animations.add(mig.getMigAnimation());
        mig.getMigAnimation().play();
        game.migs.getChildren().add(mig);
    }

    public static void createTrees(Game game, Pane pane) {
        Tree tree = new Tree(game);
        game.trees.getChildren().add(tree);
        Tree tree1 = new Tree(game);
        game.trees.getChildren().add(tree1);
    }

    public static void createTrenches(Game game, Pane pane) {
        Trench trench = new Trench(game);
        game.trenches.getChildren().add(trench);
    }

    public static void createBuildings(Game game, Pane pane) {
        Building building = new Building(game);
        game.buildings.getChildren().add(building);
    }

    private static Button createButtonPause(Wave1 wave1) {
        Button button = new Button();
        button.setText("Pause");
        button.setOnMouseClicked(pauseClicked(wave1));
        button.setFocusTraversable(false);
        return button;
    }

    private static EventHandler<? super MouseEvent> pauseClicked(Wave1 wave1) {
        EventHandler<? super MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!wave1.isPause()) {
                    for (Transition animation: wave1.getGame().animations) {
                        animation.pause();
                    }
                    PauseMain pauseMain = new PauseMain();
                    try {
                        pauseMain.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        return eventHandler;
    }

    private static Button createButtonPause2(Wave2 wave2) {
        Button button = new Button();
        button.setText("Pause");
        button.setOnMouseClicked(pauseClicked2(wave2));
        button.setFocusTraversable(false);
        return button;
    }

    private static EventHandler<? super MouseEvent> pauseClicked2(Wave2 wave2) {
        EventHandler<? super MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!wave2.isPause()) {
                    for (Transition animation: wave2.getGame().animations) {
                        animation.pause();
                    }
                    PauseMain pauseMain = new PauseMain();
                    try {
                        pauseMain.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        return eventHandler;
    }

    private static Button createButtonPause3(Wave3 wave3) {
        Button button = new Button();
        button.setText("Pause");
        button.setOnMouseClicked(pauseClicked3(wave3));
        button.setFocusTraversable(false);
        return button;
    }

    private static EventHandler<? super MouseEvent> pauseClicked3(Wave3 wave3) {
        EventHandler<? super MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!wave3.isPause()) {
                    for (Transition animation: wave3.getGame().animations) {
                        animation.pause();
                    }
                    PauseMain pauseMain = new PauseMain();
                    try {
                        pauseMain.start(StartMain.getStageStart());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        return eventHandler;
    }


    private static Button showUsername(String username) {
        Button button = new Button();
        button.setText(username);
        button.setFocusTraversable(false);
        button.setBackground(null);
        return button;
    }

    public static VBox createScoreboardWave1(Wave1 wave1, String username) {
        VBox vBox = new VBox();
        Button buttonPause = createButtonPause(wave1);
        vBox.getChildren().add(buttonPause);
        Button buttonUsername = showUsername(username);
        vBox.getChildren().add(buttonUsername);
        Label labelWaveName = createLabelWave1();
        vBox.getChildren().add(labelWaveName);
        Label labelAccuracy = createLabelAccuracy();
        vBox.getChildren().add(labelAccuracy);

        return vBox;
    }

    private static Label createLabelWave1() {
        Label label = new Label();
        label.setText(Wave1.name);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }


    public static VBox createScoreboardWave2(Wave2 wave2, String username) {
        VBox vBox = new VBox();
        Button buttonPause = createButtonPause2(wave2);
        vBox.getChildren().add(buttonPause);
        Button buttonUsername = showUsername(username);
        vBox.getChildren().add(buttonUsername);
        Label labelWaveName = createLabelWave2();
        vBox.getChildren().add(labelWaveName);
        Label labelAccuracy = createLabelAccuracy();
        vBox.getChildren().add(labelAccuracy);

        return vBox;
    }

    private static Label createLabelWave2() {
        Label label = new Label();
        label.setText(Wave2.name);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }

    public static VBox createScoreboardWave3(Wave3 wave3, String username) {
        VBox vBox = new VBox();
        Button buttonPause = createButtonPause3(wave3);
        vBox.getChildren().add(buttonPause);
        Button buttonUsername = showUsername(username);
        vBox.getChildren().add(buttonUsername);
        Label labelWaveName = createLabelWave3();
        vBox.getChildren().add(labelWaveName);
        Label labelAccuracy = createLabelAccuracy();
        vBox.getChildren().add(labelAccuracy);

        return vBox;
    }

    private static Label createLabelAccuracy() {
        Label label = new Label();
        label.setText("accuracy: " + findAccuracy());
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private static String findAccuracy() {
        int numberOfShoots = User.getCurrentUser().getMainMenuMain().getInformationOfGame().getNumberOfShoots();
        int numberOfKills = User.getCurrentUser().getMainMenuMain().getInformationOfGame().getNumberOfKills();
        if (numberOfShoots == 0) return 0 + "%";
        double accuracy = ((double) numberOfKills * 100) / (double) numberOfShoots;
        String string = new DecimalFormat("0.00").format(accuracy) + "%";
        return string;
    }

    private static Label createLabelWave3() {
        Label label = new Label();
        label.setText(Wave3.name);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }

}
