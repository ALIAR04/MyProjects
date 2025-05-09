package model.cards.definitionCards;

import model.abilities.Ability;

import java.util.ArrayList;

public class CommanderCard extends SpecialCard{

    public CommanderCard(String factinName, int numberOfCardInFaction, String name, Ability ability, String backgroundAddress) {
        super(factinName, numberOfCardInFaction, name, new ArrayList<>(),
                true, false, backgroundAddress, ability);
    }
}
