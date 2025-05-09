package model.cards.weatherCards;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.abilities.weatherAbilities.TorrentialRainAbility;
import model.cards.definitionCards.WeatherCard;
import view.Launcher;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class TorrentialRain extends WeatherCard {
    public TorrentialRain() {
        super("Neutral", 3, "TorrentialRain", "/cards/neutral/weather/weather_rain.jpg"
        ,new TorrentialRainAbility());
    }
}
