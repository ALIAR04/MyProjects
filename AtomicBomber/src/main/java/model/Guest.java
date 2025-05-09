package model;

import javafx.animation.Timeline;
import view.Wave1;

import java.util.ArrayList;

public class Guest {
    public ArrayList<Guest> guests = new ArrayList<>();
    private String username;
    private Wave1 wave1;

    public Guest() {
        int number = guests.size();
        this.username = "guest-" + number;
        guests.add(this);
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWave1(Wave1 wave1) {
        this.wave1 = wave1;
    }


}
