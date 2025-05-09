package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.weatherCards.BitingFrost;
import model.cards.definitionCards.Card;
import model.game.Player;

public class PureBloodElfAbility extends Ability {
    public PureBloodElfAbility() {
        super("Pureblood Elf Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        for (Card card: turnPlayer.getCurrentDeck().getCards().keySet()){
            if(card instanceof BitingFrost){
                int amount = turnPlayer.getCurrentDeck().getCards().get(card);
                if (amount > 0)
                    turnPlayer.put(card.clone(true));
                turnPlayer.getCurrentDeck().getCards().put(card, amount - 1);
                if (amount == 1)
                    turnPlayer.getCurrentDeck().getCards().remove(card);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Pick a Biting Frost card from your deck and play it instantly.";
    }
}
