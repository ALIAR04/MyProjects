package model.cards.definitionCards;

import model.abilities.Ability;

import java.util.ArrayList;

public class UnitCard extends Card{
    public UnitCard(String factionName, int numberOfCardInFaction, String name, int power, boolean hero,
                    ArrayList<Integer> acceptableRows, Ability ability, String backgroundAddress) {
        super(factionName, numberOfCardInFaction, name, false,
                false, hero, true, false, acceptableRows, backgroundAddress, ability);
        this.power = power;
        this.originalPower = power;
        this.beforeTransformPower = power;
    }
    @Override
    public Card clone(boolean originalPower) {
        int power;
        if (originalPower)
            power = this.originalPower;
        else power = this.getPower();
        return new UnitCard(
                this.getFactionName(),
                this.getNumberOfCardInFaction(),
                this.getName(),
                power,
                this.isHero(),
                this.getAcceptableRows(),
                this.getAbility(),
                this.getImageAddress()
        );
    }
}
