package model.cards.weatherCards;


import model.abilities.weatherAbilities.SkelligeStormAbility;
import model.cards.definitionCards.WeatherCard;

public class SkelligeStorm extends WeatherCard {

    public SkelligeStorm() {
        super("Neutral", 3, "Skellige Storm", "/cards/neutral/weather/weather_storm.jpg",
                new SkelligeStormAbility());
    }
}
