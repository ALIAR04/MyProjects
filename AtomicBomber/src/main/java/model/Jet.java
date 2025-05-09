package model;

import com.sun.javafx.scene.traversal.Direction;
import controller.JetAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.StartMain;
import view.Wave2;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class Jet extends Rectangle {
    private Game game;
    private final double width = 50;
    private final double height = 50;
    public Image image;
    private int degree;
    private int direction = 0;
    private JetAnimation jetAnimation;

    public Jet(Game game) {
        super(50,50);
        this.game = game;
        setX((game.width - width) / 2);
        setY(250);
        image = new Image(Jet.class.getResource("/Images/jetright.png").toExternalForm());
        setFill(new ImagePattern(image));
        degree = 0;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setJetAnimation(JetAnimation jetAnimation) {
        this.jetAnimation = jetAnimation;
    }
}
