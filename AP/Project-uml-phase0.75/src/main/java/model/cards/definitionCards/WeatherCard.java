package model.cards.definitionCards;

import model.abilities.Ability;

import java.util.ArrayList;
import java.util.Arrays;

public class WeatherCard extends SpecialCard{
    public WeatherCard(String factionName, int numberOfCardInFaction, String name, String backgroundAddress,
                       Ability ability) {
        super(factionName, numberOfCardInFaction, name,
                new ArrayList<>(),
                false, true, backgroundAddress, ability);
    }
}
