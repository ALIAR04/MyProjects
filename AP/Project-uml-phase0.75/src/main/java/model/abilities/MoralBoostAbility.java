package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;

public class MoralBoostAbility extends  Ability {
    public MoralBoostAbility() {
        super("Moral boost Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        for(Card card: currentCard.getRow().getCards()){
            if (card.getPower() != -1 && !card.isHero() && !card.equals(currentCard))
                card.setPower(card.getPower() + 1);
        }
    }

    @Override
    public String getDescription() {
        return "Adds +1 to all units in the row (excluding itself).";
    }
}
