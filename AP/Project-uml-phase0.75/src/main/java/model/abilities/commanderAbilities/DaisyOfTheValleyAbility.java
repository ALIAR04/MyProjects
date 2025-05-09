package model.abilities.commanderAbilities;

import model.abilities.Ability;
import model.cards.definitionCards.Card;
import model.cards.definitionCards.Cards;
import model.game.Player;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class DaisyOfTheValleyAbility extends Ability {
    public DaisyOfTheValleyAbility() {
        super("Daisy Of The Valley Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        Card card = pickRandomCardFromCardsEnum();
        turnPlayer.addCardToHand(card);
    }

    private Card pickRandomCardFromCardsEnum() {
        EnumSet<Cards> allCards = EnumSet.allOf(Cards.class);
        ArrayList<Cards> cardsList = new ArrayList<>(allCards);
        Random random = new Random(cardsList.size());
        int index = random.nextInt();
        return cardsList.get(index).getCard();
    }

    @Override
    public String getDescription() {
        return "Draw an extra card at the beginning of the battle.";
    }
}
