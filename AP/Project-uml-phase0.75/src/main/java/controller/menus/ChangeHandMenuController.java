package controller.menus;

import model.cards.definitionCards.Card;
import model.game.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ChangeHandMenuController {
    public static void vetoCard(Player player){
        ArrayList<Card> hand = getRandomHandFromDeck(player);
        for (int i = 0; i < 2; i++) {
            //call get Random Card from deck
        }
        //start the game and go to the game menu
    }
    public static void passRound(Player player, ArrayList<Card> hand){
        player.setHand(hand);
        //start game and go to the game menu
    }

    public static Card getRandomCardFromDeck(Player player){
        ArrayList<Card> cardsInDeck = player.getCardsInDeck();
        Random random = new Random();
        int randomIndex = random.nextInt(cardsInDeck.size());
        Card newCard = cardsInDeck.get(randomIndex);
        cardsInDeck.remove(randomIndex);
        return newCard;
    }

    public static ArrayList<Card> getRandomHandFromDeck(Player player){
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> hand = new ArrayList<>();
        Random random = new Random();
        for (HashMap.Entry<Card, Integer> set : player.getCurrentDeck().getCards().entrySet()) {
            for (int i = 0; i < set.getValue(); i++) {
                cards.add(set.getKey());
            }
        }
        player.setCardsInDeck(cards);
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(cards.size());
            hand.add(cards.get(randomIndex));
            cards.remove(randomIndex);
        }
        return hand;
    }
}
