package model.abilities;

import model.cards.definitionCards.Card;
import model.cards.definitionCards.Cards;
import model.game.Player;
import model.game.Row;

public class BerserkerAbility extends Ability {

    public BerserkerAbility() {
        super("Berserker Ability");
    }

    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        Row row = currentCard.getRow();
        row.getCards().remove(currentCard);
        Card newCard = Cards.VILDKAARL.getCard();
        newCard.setRow(row);
        row.getCards().add(newCard);
        currentCard.neutralizeAbility();
    }

    @Override
    public String getDescription() {
        return "Transforms into a bear when a Mardroeme card is on its row.";
    }
}
