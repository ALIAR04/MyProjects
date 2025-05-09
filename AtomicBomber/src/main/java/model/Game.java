package model;

import javafx.animation.Transition;
import view.Wave1;
import javafx.scene.Group;
import java.util.ArrayList;

public class Game {
    public final double width = 1100;
    public final double height = 600;
    public Group tanks = new Group();
    public Group trucks = new Group();
    public Group trees = new Group();
    public Group trenches = new Group();
    public Group buildings = new Group();
    public Group migs = new Group();
    public ArrayList<Transition> animations = new ArrayList<>();
    private int kills;

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

}
