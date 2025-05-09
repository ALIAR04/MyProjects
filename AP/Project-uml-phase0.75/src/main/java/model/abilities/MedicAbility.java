package model.abilities;

import model.cards.definitionCards.Card;
import model.game.Player;
import model.game.Row;
import view.Terminal;

import java.util.ArrayList;
import java.util.Random;

public class MedicAbility extends Ability {
    public MedicAbility() {
        super("Medic Ability");
    }
    ArrayList<Card> discardPileForMedicAbility = new ArrayList<>();
    @Override
    public void doAbility(Card currentCard, Player turnPlayer, Player enemyPlayer) {
        Terminal terminal = Terminal.getTerminal();

        int index = 1;
        for (Card card : turnPlayer.getDiscardPile()) {
            if (!card.isSpecial()) discardPileForMedicAbility.add(card);
            terminal.println(index + "-" + card);
        }
        if (!discardPileForMedicAbility.isEmpty())
            terminal.println("choose one of the above cards to play.");
        //show discardPileForMedicAbility
        //TODO
        //give a card from discardpile

    }

    public ArrayList<Card> getDiscardPileForMedicAbility() {
        return discardPileForMedicAbility;
    }

    private void drawCardFromDiscardPile(Player turnPlayer, Card card, Player enemyPlayer) {
        turnPlayer.getDiscardPile().remove(card);
        Random random = new Random();
        if (card.getAbility() instanceof SpyAbility) {
            int randomNumber = random.nextInt(card.getAcceptableRows().size());
            Row row = enemyPlayer.getRows().get(card.getAcceptableRows().get(randomNumber));
            card.setRow(row);
            row.getCards().add(card);
        } else {
            int randomNumber = random.nextInt(card.getAcceptableRows().size());
            Row row = turnPlayer.getRows().get(card.getAcceptableRows().get(randomNumber));
            card.setRow(row);
            row.getCards().add(card);
        }
    }

    @Override
    public String getDescription() {
        return "Choose one card from your discard pile and play it instantly (no Heroes or Special Cards).";
    }
}
