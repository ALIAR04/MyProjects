package model.cards.definitionCards;

import model.abilities.Ability;

import java.util.ArrayList;
import java.util.Arrays;

public class SpellCard extends SpecialCard{
    public SpellCard(String factionName, int numberOfCardInFaction, String name, Ability ability, String backgroundAddress) {
        super(factionName, numberOfCardInFaction,
                name, new ArrayList<>(Arrays.asList(1, 2, 3)),
                false, true, backgroundAddress, ability);
    }
}
