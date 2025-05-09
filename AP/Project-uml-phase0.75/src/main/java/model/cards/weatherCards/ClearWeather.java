package model.cards.weatherCards;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.abilities.weatherAbilities.ClearWeatherAbility;
import model.cards.definitionCards.WeatherCard;
import view.Launcher;

import java.util.Objects;

public class ClearWeather extends WeatherCard {
    public ClearWeather() {
        super("Neutral", 3, "Clear Weather", "/cards/neutral/weather/weather_clear.jpg"
        ,new ClearWeatherAbility());
    }
}
