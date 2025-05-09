package model.cards.weatherCards;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.abilities.weatherAbilities.ImpenetrableFogAbility;
import model.cards.definitionCards.WeatherCard;
import view.Launcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ImpenetrableFog extends WeatherCard {
    public ImpenetrableFog() {
        super("Neutral", 3, "Interminable Fog", "/cards/neutral/weather/weather_fog.jpg",
                new ImpenetrableFogAbility());
    }
}
