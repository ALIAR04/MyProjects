package controller;

import model.User;
import model.abilities.*;
import model.abilities.weatherAbilities.*;
import model.cards.definitionCards.Card;
import model.cards.definitionCards.CommanderCard;
import model.cards.definitionCards.UnitCard;
import model.game.*;
import view.Terminal;

import java.util.*;
import java.util.regex.Matcher;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameController {
    private Player player1;
    private  Player player2;
    private boolean active1 = true;
    private int turnNumber = 1;
    private Card medicCard;
    private  boolean player1HasPassed = false;
    private  boolean player2HasPassed = false;
    private boolean medicPlayed = false;
    private  ArrayList<Round> rounds;
    private  int roundNumber;

    private  ArrayList<Card> spellsOfPlayer1 = new ArrayList<>();
    private  ArrayList<Card> spellsOfPlayer2 = new ArrayList<>();
    private static GameController currentGame;

    public static void setCurrentGame(GameController currentGame) {
        GameController.currentGame = currentGame;
    }

    public static GameController getCurrentGame() {
        return currentGame;
    }

    

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Card getMedicCard() {
        return medicCard;
    }

    public void setMedicCard(Card medicCard) {
        this.medicCard = medicCard;
    }

    public ArrayList<Card> getInHandDeck(Player player) {
        return player.getHand();
    }

    public int getRemainingNumberOfCards(Player player) {
        return player.getCardsInDeck().size();
    }

    public ArrayList<Card> getOutOfPlaceCards(Player player) {
        return player.getDiscardPile();
    }

    public ArrayList<Card> getCardsInRow(int rowNumber, Player player) {
        for (Row row : player.getRows()) {
            if (row.getRowNumber() == rowNumber) return row.getCards();
        }
        return null;
    }

    public boolean isMedicPlayed() {
        return medicPlayed;
    }

    public void setMedicPlayed(boolean medicPlayed) {
        this.medicPlayed = medicPlayed;
    }

    public ArrayList<Card> getSpellsInPlay() {
        ArrayList<Card> spells = spellsOfPlayer1;
        spells.addAll(spellsOfPlayer2);
        return spells;
    }

    public Card getCommander(Player player) {
        return player.getCurrentDeck().getCommanderCard();
    }

    public String getNameOfPlayer(Player player) {
        return player.getUser().getUsername();
    }

    public String getFactionOfPlayer(Player player) {
        return player.getCurrentDeck().getFaction().getName();
    }

    public void playCommanderPower(Player player) {
        CommanderCard commanderCard = (CommanderCard) player.getCommander();
        if (player.equals(player1)) commanderCard.doAbility(player1, player2);
        else commanderCard.doAbility(player2, player1);
    }

    public int getTotalScoreOfRow(Row row) {
        return row.getTotalScore();
    }

    public int getTotalScore(Player player) {
        return player.getTotalScore();
    }

    public void passRound(Player player) {
        active1 = !active1;
        if (player.getUser().getUsername().equals(player1.getUser().getUsername())) player1HasPassed = true;
        else player2HasPassed = true;
        if ((player1HasPassed && player2HasPassed) || player1.getHand().isEmpty() || player2.getHand().isEmpty())
            endRound();
        else{
            turnNumber++;
            Terminal.getTerminal().println(getTurnPlayer().getUser().getUsername() + "'s turn!");
        }
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void endRound() {
        int scorePlayer1 = player1.getTotalScore();
        int scorePlayer2 = player2.getTotalScore();
        Player winner = takeWinner(scorePlayer1, scorePlayer2);
        addRound(new Round(player1, player2, scorePlayer1, scorePlayer2, winner));
        if (winner == null) {
            player1.decreaseLifeByOne();
            player2.decreaseLifeByOne();
        } else if (winner.getUser().getUsername().equals(player1.getUser().getUsername())) player2.decreaseLifeByOne();
        else player1.decreaseLifeByOne();
        if (checkTheEndGame()) endGame();
        cleanTheBoard(winner);
        Terminal.getTerminal().println("round: " + (this.rounds.size()) + " "
                + player1.getUser().getUsername() + "'s total score: " + scorePlayer1 + " " +
                player2.getUser().getUsername() + "'s total score: " + scorePlayer2);
        player1.setTotalScore(0);
        player2.setTotalScore(0);
        if (!checkTheEndGame()){
            Terminal.getTerminal().println("new round started!");
            Terminal.getTerminal().println(getTurnPlayer().getUser().getUsername() + "'s turn:");
        }
    }

    private boolean checkTheEndGame() {
        return player1.getLife() == 0 || player2.getLife() == 0;
    }

    private void cleanTheBoard(Player winner) {
        ArrayList<Card> cardsToDiscardPile1 = new ArrayList<>();
        ArrayList<Card> cardsToDiscardPile2 = new ArrayList<>();
        sendCardsToDiscardPile(player1, cardsToDiscardPile1);
        sendCardsToDiscardPile(player2, cardsToDiscardPile2);
        if (winner != null){
            if (winner.getCurrentDeck().getFaction().getName().equals("Northern Realms")) {
                Random random = new Random();
                if (winner.getUser().getUsername().equals(player1.getUser().getUsername())) {
                    Card card = player1.getCardsInDeck().get(random.nextInt(player1.getCardsInDeck().size()));
                    player1.getCardsInDeck().remove(card);
                    player1.getHand().add(card);
                } else {
                    Card card = player2.getCardsInDeck().get(random.nextInt(player2.getCardsInDeck().size()));
                    player2.getCardsInDeck().remove(card);
                    player2.getHand().add(card);
                }
            }
        }
        sendCardsToDiscardPile(player1, spellsOfPlayer1);
        sendCardsToDiscardPile(player2, spellsOfPlayer2);
        checkSkellige(player1);
        checkSkellige(player2);
        checkTransformer(player1, player2);

    }

    private void checkTransformer(Player player1, Player player2) {
        ArrayList<Row> allRows = new ArrayList<>(player1.getRows());
        allRows.addAll(player2.getRows());
        for (Row row : allRows) {
            for (Card card : row.getCards()) {
                if (card.getAbility() instanceof TransformerAbility) {
                    card.doAbility(player1, player2);
                }
            }
        }
    }
    public void goToNextTurn(){
        turnNumber++;
        active1 = !active1;
        player1HasPassed = false;
        player2HasPassed = false;
        doAbilityOfCards();
        Terminal.getTerminal().println(getTurnPlayer().getUser().getUsername() + "'s turn!");
    }
    private void checkSkellige(Player player) {
        ArrayList<Card> unitCardsInDiscardPile = new ArrayList<>();
        for (Card card : player.getDiscardPile()) {
            if (card.isUnit()) unitCardsInDiscardPile.add(card);
        }
        if (rounds.size() == 2 && player.getCurrentDeck().getFaction().getName().equals("Skellige")) {
            if (unitCardsInDiscardPile.size() >= 2) placeRandomCard(player, 2, unitCardsInDiscardPile);
            else if (unitCardsInDiscardPile.size() == 1) placeRandomCard(player, 1, unitCardsInDiscardPile);
        }
    }

    private void placeRandomCard(Player player, int number, ArrayList<Card> unitCardsInDiscardPile) {
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            Card card = unitCardsInDiscardPile.get(random.nextInt(unitCardsInDiscardPile.size()));
            placeCard(card, player,
                    player.getRows().get(card.getAcceptableRows().get(random.nextInt(card.getAcceptableRows().size()))));
            unitCardsInDiscardPile.remove(card);
            player.getDiscardPile().remove(card);
        }
    }

    private void sendCardsToDiscardPile(Player player, ArrayList<Card> cardsToDiscardPile) {
        boolean hasAUnitCard = false;
        for (Row row : player.getRows()) {
            for (Card card : row.getCards()) {
                cardsToDiscardPile.add(card);
                if (card.isUnit()) hasAUnitCard = true;
            }
        }
        UnitCard unitCard = null;
        if (player.getCurrentDeck().getFaction().getName().equals("Monsters") && hasAUnitCard) {
            while (unitCard == null) {
                Random random = new Random();
                Card tempCard = cardsToDiscardPile.get(random.nextInt(cardsToDiscardPile.size()));
                if (tempCard.isUnit()) unitCard = (UnitCard) tempCard;
            }
        }
        for (Row row : player.getRows()) {
            for (int i = 0; i < row.getCards().size(); i++) {
                Card card = row.getCards().get(i);
                if (!card.equals(unitCard)) {
                    player.getDiscardPile().add(card);
                    row.getCards().remove(card);
                }
            }
        }
    }
    private Player takeWinner(int scorePlayer1, int scorePlayer2) {
        Player winner;
        if (scorePlayer1 > scorePlayer2) winner = player1;
        else if (scorePlayer2 > scorePlayer1) winner = player2;
        else if (player1.getCurrentDeck().getFaction().getName().equals("Empire Nilfgaardian") &&
                !player2.getCurrentDeck().getFaction().getName().equals("Empire Nilfgaardian")) winner = player1;
        else if (player2.getCurrentDeck().getFaction().getName().equals("Empire Nilfgaardian") &&
                !player1.getCurrentDeck().getFaction().getName().equals("Empire Nilfgaardian")) winner = player2;
        else winner = null;
        return winner;
    }

    public void endGame() {
        Player winner;
        int winPlayer1 = 0, winPlayer2 = 0;
        int finalScorePlayer1 = 0, finalScorePlayer2 = 0;
        ArrayList<Integer> scorePlayer1 = new ArrayList<>(), scorePlayer2 = new ArrayList<>();
        for (Round round : rounds) {
            finalScorePlayer1 += round.getPlayer1Score();
            finalScorePlayer2 += round.getPlayer2Score();
            scorePlayer1.add(round.getPlayer1Score());
            scorePlayer2.add(round.getPlayer2Score());
            if (round.getWinner().getUser().getUsername().equals(player1.getUser().getUsername())) winPlayer1++;
            else if (round.getWinner().getUser().getUsername().equals(player2.getUser().getUsername())) winPlayer2++;
        }
        if (winPlayer1 < winPlayer2) winner = player2;
        else if (winPlayer1 > winPlayer2) winner = player1;
        else winner = null;
        Date currentDate = new Date(System.currentTimeMillis());
        User winnerUser = null;
        if (winner != null) winnerUser = winner.getUser();
        GameResult gameResult = new GameResult(player1.getUser()
                , player2.getUser(), currentDate, scorePlayer1, scorePlayer2,
                finalScorePlayer1, finalScorePlayer2, rounds, winnerUser);
        player1.getUser().updateGameHistory(gameResult, false);
        player2.getUser().updateGameHistory(new GameResult(player2.getUser()
                , player1.getUser(), currentDate, scorePlayer1, scorePlayer2,
                finalScorePlayer1, finalScorePlayer2, rounds, winnerUser), true);
        Terminal.getTerminal().println("Game finished!");
        Terminal.getTerminal().println(gameResult.toString());
    }
    public void addRound(Round round) {
        if (rounds == null) rounds = new ArrayList<>();
        rounds.add(round);
    }

    public int getNumberOfLife(Player player) {
        return player.getLife();
    }

    public int getNumberOfCardsInHand(Player player) {
        return player.getHand().size();
    }

    public Player getTurnPlayer() {
        if (active1) return player1;
        else return player2;
    }
    public Player getNotTurnPlayer(){
        if (active1) return player2;
        else return player1;
    }

    public void placeCard(Card card, Player player, Row row) {
        row.getCards().add(card);
        player.getHand().remove(card);
        card.setRow(row);
    }

    public void placeSpellCard(Card card, Player player) {
        if (player.getUser().getUsername().equals(player1.getUser().getUsername())) {
            spellsOfPlayer1.add(card);
        } else {
            spellsOfPlayer2.add(card);
        }
        player.getHand().remove(card);
    }

    public void doAbilityOfCards() {
        ArrayList<Row> allRows = new ArrayList<>(player1.getRows());
        allRows.addAll(player2.getRows());
        Player turnPlayer, enemyPlayer;
        if (active1) {
            turnPlayer = player1;
            enemyPlayer = player2;
        } else {
            turnPlayer = player2;
            enemyPlayer = player1;
        }

        doingAbilitiesBeforeSetOriginalPower(allRows, turnPlayer, enemyPlayer);

        setOriginalPower(allRows);

        doingAbilitiesAfterSetOriginalPower(allRows, turnPlayer, enemyPlayer);

        for (Row row : allRows) {
            for (Card card : row.getCards()) {
                System.out.println(card.getName() + ": " + card.getPower());
            }
        }

    }

    private void doingAbilitiesBeforeSetOriginalPower(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        doScorchAbility(allRows, turnPlayer, enemyPlayer);
        doSpyAbility(allRows, turnPlayer, enemyPlayer);
    }

    private void setOriginalPower(ArrayList<Row> allRows) {
        for (Row row : allRows) {
            for (Card card : row.getCards()) {
                if (card.hasPower() && card.isUnit()) {
                    UnitCard unitCard = (UnitCard) card;
                    unitCard.setPower(unitCard.getOriginalPower());
                }
            }
        }
    }

    private void doingAbilitiesAfterSetOriginalPower(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        doMusterAbility(allRows, turnPlayer, enemyPlayer);
        doMardoemeAbility(allRows, turnPlayer, enemyPlayer);
        doWeatherAbility(turnPlayer, enemyPlayer);
        doTightBondAbility(allRows, turnPlayer, enemyPlayer);
        doMoralBoostAbility(allRows, turnPlayer, enemyPlayer);
        doHornAbility(allRows, turnPlayer, enemyPlayer);
    }

    private void doWeatherAbility(Player turnPlayer, Player enemyPlayer) {
        ArrayList<Card> allSpells = new ArrayList<>(spellsOfPlayer1);
        allSpells.addAll(spellsOfPlayer2);
        for (Card card : allSpells) {
            if (card.getAbility() instanceof BitingFrostAbility) card.doAbility(turnPlayer, enemyPlayer);
            else if (card.getAbility() instanceof ImpenetrableFogAbility) card.doAbility(turnPlayer, enemyPlayer);
            else if (card.getAbility() instanceof TorrentialRainAbility) card.doAbility(turnPlayer, enemyPlayer);
            else if (card.getAbility() instanceof SkelligeStormAbility) card.doAbility(turnPlayer, enemyPlayer);
            else if (card.getAbility() instanceof ClearWeatherAbility) card.doAbility(turnPlayer, enemyPlayer);
        }
    }

    private void doMusterAbility(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        for (Row row : allRows) {
            ArrayList<Card> cardsInRow = row.getCards();
            for (int i = 0; i < cardsInRow.size(); i++) {
                Card card = cardsInRow.get(i);
                if (card.getAbility() instanceof MusterAbility) {
                    if (row.getPlayer().getUser().getUsername().equals(player1.getUser().getUsername()))
                        card.doAbility(player1, player2);
                    else if (row.getPlayer().getUser().getUsername().equals(player2.getUser().getUsername()))
                        card.doAbility(player2, player1);
                    card.setAbility(new NullAbility());
                }
            }
        }
    }

    private void doMardoemeAbility(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        for (Row row : allRows) {
            ArrayList<Card> cardsInRow = row.getCards();
            for (int i = 0; i < cardsInRow.size(); i++) {
                Card card = cardsInRow.get(i);
                if (card.getAbility() instanceof MardoemeAbility) {
                    cardsInRow.get(i).doAbility(turnPlayer, enemyPlayer);
                    card.setAbility(new NullAbility());
                }
            }
        }
    }

    private void doTightBondAbility(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        for (Row row : allRows) {
            for (Card card : row.getCards()) {
                if (card.getAbility() instanceof TightBondAbility) {
                    card.doAbility(turnPlayer, enemyPlayer);
                }
            }
        }
    }

    private void doMoralBoostAbility(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        for (Row row : allRows) {
            ArrayList<Card> cardsInRow = row.getCards();
            for (int i = 0; i < cardsInRow.size(); i++) {
                if (cardsInRow.get(i).getAbility() instanceof MoralBoostAbility) {
                    cardsInRow.get(i).doAbility(turnPlayer, enemyPlayer);
                }
            }
        }
    }

    private void doHornAbility(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        for (Row row : allRows) {
            ArrayList<Card> cardsInRow = row.getCards();
            for (int i = 0; i < cardsInRow.size(); i++) {
                if (cardsInRow.get(i).getAbility() instanceof HornAbility) {
                    cardsInRow.get(i).doAbility(turnPlayer, enemyPlayer);
                }
            }
        }
    }

    private void doScorchAbility(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        for (Row row : allRows) {
            ArrayList<Card> cardsInRow = row.getCards();
            for (int i = 0; i < cardsInRow.size(); i++) {
                Card card = cardsInRow.get(i);
                if (card.getAbility() instanceof ScorchAbility) {
                    card.doAbility(turnPlayer, enemyPlayer);
                    card.setAbility(new NullAbility());
                }
            }
        }
    }

    private void doMedicAbility(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        for (Row row : allRows) {
            for (int i = 0; i < row.getCards().size(); i++) {
                Card card = row.getCards().get(i);
                if (card.getAbility() instanceof MedicAbility) {
                    card.doAbility(turnPlayer, enemyPlayer);
                    card.setAbility(new NullAbility());
                }
            }
        }
    }

    private void doSpyAbility(ArrayList<Row> allRows, Player turnPlayer, Player enemyPlayer) {
        for (Row row : allRows) {
            for (Card card : row.getCards()) {
                if (card.getAbility() instanceof SpyAbility) {
                    if (row.getPlayer().getUser().getUsername().equals(player1.getUser().getUsername()))
                        card.doAbility(player2, player1);
                    else card.doAbility(player1, player2);
                    card.neutralizeAbility();
                }
            }
        }
    }

    public String vetoCard(Matcher matcher) {
        Player player = getTurnPlayer();
        if (turnNumber > 2)
            return "it is turn " + turnNumber + " !\n";
        if (player.getNumberOfVetosPlayed() >= 2)
            return "you have already vetoed twice!\n";
        else{
            int num = Integer.parseInt(matcher.group(1));
            Random random = new Random();
            ArrayList<Card> cards = player.getCardsInDeck();
            Card cardToAdd = cards.get(random.nextInt(0, cards.size()));
            Card cardToRemove = player.getHand().get(num - 1);
            player.getHand().remove(cardToRemove);
            player.getHand().add(cardToAdd);
            player.addNumberOfVetoesPlayedByOne();
        }
        return "card vetoed!";
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public static Paint getColorOfFaction(String name) {
        if(name.equals("Monsters")) {
            return Color.valueOf("#ff765e");
        } else if(name.equals("Empire Nilfgaardian")) {
            return Color.valueOf("#eba000");
        } else if(name.equals("Northern Realms")) {
            return Color.valueOf("#8ea2ff");
        } else if(name.equals("Scoia'tael")) {
            return Color.valueOf("#80f700");
        } else if(name.equals("Skellige")) {
            return Color.valueOf("#e75eff");
        }
        return null;
    }

    public boolean isActive1() {
        return active1;
    }

    public boolean isPlayer1HasPassed() {
        return player1HasPassed;
    }

    public boolean isPlayer2HasPassed() {
        return player2HasPassed;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public ArrayList<Card> getSpellsOfPlayer1() {
        return spellsOfPlayer1;
    }

    public ArrayList<Card> getSpellsOfPlayer2() {
        return spellsOfPlayer2;
    }
}
