package model.cards.definitionCards;

import model.abilities.Ability;

import java.util.ArrayList;

public class SpecialCard extends Card {
    public SpecialCard(String factionName, int numberOfCardInFaction,
                       String name, ArrayList<Integer> acceptableRows,
                       boolean commander, boolean spell, String backgroundAddress, Ability ability) {
        super(factionName, numberOfCardInFaction, name, commander,
                true, false, false, spell, acceptableRows, backgroundAddress, ability);
    }
    @Override
    public Card clone(boolean originalPower) {
        return new SpecialCard(
                this.getFactionName(),
                this.getNumberOfCardInFaction(),
                this.getName(),
                this.getAcceptableRows(),
                this.isCommander(),
                this.isSpell(),
                this.getImageAddress(),
                this.ability
        );
    }
}
