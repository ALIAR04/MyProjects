package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.game.Player;

public class SiegeMasterAbility extends Ability {
    public SiegeMasterAbility() {
        super("Siege Master Ability");
    }
    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        for (Card card: turnPlayer.getCurrentDeck().getCards().keySet()){
            if (card.getName().equals("Interminable Fog")){
                turnPlayer.put(card.clone(true));
                break;
            }
        }
    }

    @Override
    public String getDescription() {
        return "Pick an Impenetrable Fog card from your deck and play it instantly.";
    }

}
