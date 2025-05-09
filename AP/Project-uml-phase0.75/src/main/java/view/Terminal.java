package view;
import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.abilities.MedicAbility;
import model.cards.definitionCards.Card;
import model.game.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Terminal extends Application {
    private TextArea terminalDisplay;
    private GameController game;
    private static Terminal terminal = new Terminal();
    public static Terminal getTerminal(){
        return terminal;
    }
    public void start(Stage primaryStage) {
        terminalDisplay = new TextArea();
        terminalDisplay.setEditable(true); // Allow user input

        terminalDisplay.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String[] lines = terminalDisplay.getText().trim().split("\n");
                String userInput = lines[lines.length - 1];
                processUserInput(userInput);
                event.consume();
            }
        });

        StackPane root = new StackPane(terminalDisplay);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Terminal");
        primaryStage.show();
    }
    private void processUserInput(String userInput) {
        if (userInput.equals("start game")){
            println("game started!");
            game = GameController.getCurrentGame();
            println(game.getTurnPlayer().getUser().getUsername() + "'s turn:");
        }
        String regex = "veto card (\\d+)$";
        Matcher matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            String response = game.vetoCard(matcher);
            println(response);
            if (response.equals("card vetoed!"))
                printCardsIn(game.getTurnPlayer().getHand());
            return;
        }
        regex = "in hand deck$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            printCardsIn(game.getTurnPlayer().getHand());
            return;
        }
        regex = "in hand deck (\\d+)$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showCardsInHand(matcher);
            return;
        }
        regex = "remaining cards to play$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showRemainingcards(matcher);
            return;
        }
        regex = "out of play cards$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showDiscardCards();
            return;
        }
        regex = "cards in row (\\d)$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showCardsInRow(matcher);
            return;
        }
        regex = "spells in play$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            printCardsIn(game.getSpellsInPlay());
            return;
        }
        regex = "place card (\\d+)( in row (\\d))?$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            println("putting card...");
            if (!game.isMedicPlayed())
                placeCard(matcher);
            else medicPlaceCard(matcher);
            return;
        }
        regex = "show commander$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            Card card = game.getCommander(game.getTurnPlayer());
            println("your commander: " + card);
            println("commander's ability: " + card.getAbility().getName());
            return;
        }
        regex = "commander power play$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            game.getTurnPlayer().getCommander()
                    .doAbility(game.getTurnPlayer(), game.getNotTurnPlayer());
            return;
        }
        regex = "show players info$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showPlayersInfo();
            return;
        }
        regex = "show players lives$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showLives();
            return;
        }
        regex = "show number of cards in hand$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showCardNumsInHand();
            return;
        }
        regex = "show turn info$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            println(game.getTurnPlayer().getUser().getUsername() + "'s turn");
            return;
        }
        regex = "show total score$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showTotalScore();
            return;
        }
        regex = "show total score of row (\\d)$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()){
            showTotalScoreOfRow(matcher);
            return;
        }
        regex = "pass round$";
        matcher = getCommandMatcher(regex, userInput);
        if (matcher.find()) {
            game.passRound(game.getTurnPlayer());
        }
    }

    private void showTotalScoreOfRow(Matcher matcher) {
        int rowNum = Integer.parseInt(matcher.group(1));
        Player player;
        if (1 <= rowNum && rowNum <= 3)
            player = game.getTurnPlayer();
        else if (4 <= rowNum && rowNum <= 6){
            player = game.getNotTurnPlayer();
            rowNum -= 3;
        }
        else{
            println("No such row");
            return;
        }
        println("total score: " + game.getTotalScoreOfRow(player.getRows().get(rowNum - 1)));
    }

    private void showTotalScore() {
        Player player = game.getTurnPlayer();
        println(player.getUser().getUsername() + "'s score: " + player.getTotalScore());
        player = game.getNotTurnPlayer();
        println(player.getUser().getUsername() + "'s score: " + player.getTotalScore());
    }

    private void showCardNumsInHand() {
        Player player = game.getTurnPlayer();
        println(player.getUser().getUsername() + "'s hand: " + player.getHand().size());
        player = game.getNotTurnPlayer();
        println(player.getUser().getUsername() + "'s hand: " + player.getHand().size());
    }

    private void showLives() {
        Player player = game.getTurnPlayer();
        println(player.getUser().getUsername()  + "'s lives: " + player.getLife());
        player = game.getNotTurnPlayer();
        println(player.getUser().getUsername() + "'s lives: " + player.getLife());
    }

    private void showPlayersInfo() {
        Player player = game.getTurnPlayer();
        println(player.getUser().getUsername() );
        println("username: " + player.getUser().getUsername());
        println("faction: " + player.getCurrentDeck().getFaction().getName());
        println("score: " + player.getTotalScore());
        println("lives: " + player.getLife());
        player = game.getNotTurnPlayer();
        println(player.getUser().getUsername() );
        println("username: " + player.getUser().getUsername());
        println("faction: " + player.getCurrentDeck().getFaction().getName());
        println("score: " + player.getTotalScore());
        println("lives: " + player.getLife());
    }
    private void medicPlaceCard(Matcher matcher) {
        MedicAbility ability = (MedicAbility) game.getMedicCard().getAbility();
        if (ability.getDiscardPileForMedicAbility() != null)
            placeCardFrom(ability.getDiscardPileForMedicAbility(), matcher);
        else {
            game.setMedicPlayed(false);
            game.setMedicCard(null);
        }
    }

    private void placeCardFrom(ArrayList<Card> cards, Matcher matcher){
        int cardNum = Integer.parseInt(matcher.group(1));
        Player player = game.getTurnPlayer();
        Card card = cards.get(cardNum - 1);
        if (card.isSpell()){
            game.placeSpellCard(card, player);
        }
        else {
            if (matcher.groupCount() < 2){
                println("You must specify a row number!");
                return;
            }
            int rowNum = Integer.parseInt(matcher.group(3));
            if (rowNum <= 3 && !card.getAcceptableRows().contains(rowNum)
                    && !card.getAbility().getName().equals("Spy Ability")){
                println("This card cannot be placed on the selected row!");
                return;
            }
            else if (rowNum <= 3 && card.getAbility().getName().equals("Spy Ability")){
                println("This card cannot be placed on the selected row!");
                return;
            }
            else if (rowNum >= 4 && card.getAbility().getName().equals("Spy Ability")
                    && !card.getAcceptableRows().contains(rowNum - 3)){
                println("This card cannot be placed on the selected row!");
                return;
            }
            else if (!card.getAbility().getName().equals("Spy Ability"))
                game.placeCard(card, player, player.getRows().get(rowNum - 1));
            else {
                player = game.getNotTurnPlayer();
                game.placeCard(card, player, player.getRows().get(rowNum - 4));
            }
        }
        println("card put successfully!");
        game.setMedicPlayed(card.getAbility() instanceof MedicAbility);
        if (!game.isMedicPlayed()){
            game.goToNextTurn();
        }
        else {
            card.doAbility(player, game.getNotTurnPlayer());
            game.setMedicCard(card);
        }
    }
    private void placeCard(Matcher matcher) {
        placeCardFrom(game.getTurnPlayer().getHand(), matcher);
    }

    private void showCardsInRow(Matcher matcher) {
        int rowNum = Integer.parseInt(matcher.group(1));
        Player player;
        if (1 <= rowNum && rowNum <= 3){
            player = game.getTurnPlayer();
            printCardsIn(player.getRows().get(rowNum - 1).getCards());
        }
        else if (4 <= rowNum && rowNum <= 6){
            player = game.getNotTurnPlayer();
            printCardsIn(player.getRows().get(rowNum - 4).getCards());
        }
    }

    private void showDiscardCards() {
        Player player = game.getTurnPlayer();
        println("Your discard pile:");
        printCardsIn(player.getDiscardPile());
        player = game.getNotTurnPlayer();
        printDividerLine();
        printDividerLine();
        println("Enemy discard pile:");
        printCardsIn(player.getDiscardPile());
    }

    public void printCardsIn(ArrayList<Card> cards) {
        int index = 1;
        for (Card card: cards){
            printCard(card, card.isHero(), index);
            index++;
        }
    }

    private void showRemainingcards(Matcher matcher) {
        println("In hand:");
        printCardsIn(game.getTurnPlayer().getHand());
        println("In deck:");
        showCardsInDeck();
    }
    private void showCardsInDeck(){
        Player player = game.getTurnPlayer();
        printCardsIn(player.getCardsInDeck());
    }
    private void showCardsInHand(Matcher matcher) {
        int num = Integer.parseInt(matcher.group(1));
        Card card = game.getTurnPlayer().getHand().get(num - 1);
        printCard(card, card.isHero(), -1);
    }

    private static Matcher getCommandMatcher(String regex, String input) {
        return Pattern.compile(regex).matcher(input);
    }
    public void println(String s){
        this.terminalDisplay.appendText(s + "\n");
    }
    public void print(String s){
        terminalDisplay.appendText(s);
    }
    public void printCard(Card card, boolean hero, int index){
        if (index == -1){
            if (hero){
                printDividerLine();
                println("name:" + card + " power:" + card.getPower() + " ability: "
                        + card.getAbility().getName() + " possible rows: " + card.getAcceptableRows());
                printDividerLine();
            }
            else println("name:" + card + " power:" + card.getPower() + " ability: "
                    + card.getAbility().getName() + " possible rows: " + card.getAcceptableRows());
        }
        else{
            if (hero){
                printDividerLine();
                println(index + "-" + "name:" + card + " power:" + card.getPower() + " ability: "
                        + card.getAbility().getName() + " possible rows: " + card.getAcceptableRows());
                printDividerLine();
            }
            else println(index + "-" + "name:" + card + " power:" + card.getPower() + " ability: "
                    + card.getAbility().getName() + " possible rows: " + card.getAcceptableRows());
        }
    }
    public void printDividerLine(){
        println("-------------------------------------------------------------------------");
    }
}
