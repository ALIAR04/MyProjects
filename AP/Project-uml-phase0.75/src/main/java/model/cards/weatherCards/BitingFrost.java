package model.cards.weatherCards;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.abilities.weatherAbilities.BitingFrostAbility;
import model.cards.definitionCards.WeatherCard;
import view.Launcher;

import java.util.Objects;

public class BitingFrost extends WeatherCard {

    public BitingFrost() {
        super("Neutral", 3,
                "Biting Frost",
                "/cards/neutral/weather/weather_frost.jpg",
                new BitingFrostAbility());
    

    }
}
