package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;

public class TransformerAbility extends Ability {
    public TransformerAbility() {
        super("Transformer Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        currentCard.setPower(8);
        currentCard.setOriginalPower(8);
        currentCard.setTransformName(currentCard.getName());
        currentCard.setName("Bovine Defence force");
        //fill the picture with after transform
    }

    @Override
    public String getDescription() {
        return "When this card is removed from the battlefield, it summons a powerful new Unit Card to take its place.";
    }
}
