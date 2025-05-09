package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.menus.PregameFXController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.cards.definitionCards.Card;
import model.cards.definitionCards.CommanderCard;
import model.game.CardGraphicalizer;
import model.game.Deck;

public class PregameFXMenu {
    
    private static PregameFXMenu INSTANCE = new PregameFXMenu();

    public static PregameFXMenu getInstance() {
        return INSTANCE;
    }

    Stage stage;
    Scene scene;
    Scanner scanner;

    public void run() throws IOException {
        stage = Launcher.getStage();
        if(scanner == null) {
            scanner = Launcher.getScanner();
        }

        scene = new Scene(FXMLLoader.load(getClass().getResource("/ui/fxml/pregame-menu.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    public static Matcher getCommandMatcher(String regex, String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    // ========================================================

    @FXML
    private HBox monstersTab, nilfgaardianTab, realmsTab, scoiataelTab, skelligeTab;

    @FXML
    private VBox allCardsGrid, deckGrid, leaderShowCase, cardInfoBox, deckInfoBox;

    @FXML
    private Label bigCardInfo, backToDeckInfoButton, chooseLeaderButton,
                    unitCardIndicator, specialCardIndicator, confirmButton,
                    saveDeckButton, loadDeckButton, turnIndicator;


    @FXML
    private void initialize() {
        turnIndicator.setText(PregameFXController.getCurrentPlayerUsername() + "'s turn");
        installTooltips();
        Deck lastDeck = PregameFXController.whatDeckToLoad();
        String initialFaction = lastDeck.getFaction().getName();
        loadFaction(initialFaction);
        loadDeck(lastDeck);
        if(lastDeck.getCommanderCard() != null) selectLeader(lastDeck.getCommanderCard());
        if(initialFaction.equals("Monsters")) monstersTab.setId("selected");
        if(initialFaction.equals("Empire Nilfgaardian")) nilfgaardianTab.setId("selected");
        if(initialFaction.equals("Northern Realms")) realmsTab.setId("selected");
        if(initialFaction.equals("Scoia'tael")) scoiataelTab.setId("selected");
        if(initialFaction.equals("Skellige")) skelligeTab.setId("selected");
    }


    private void installTooltips() {
        Tooltip monstersDescription = new Tooltip("Keeps a random unit card in the board after each round");
        monstersDescription.setFont(new Font(15));
        Tooltip nilfgaardianDescription = new Tooltip("Wins the round if it ends with a draw");
        nilfgaardianDescription.setFont(new Font(15));
        Tooltip realmsDescription = new Tooltip("Draw a card from your deck whenever you win a round");
        realmsDescription.setFont(new Font(15));
        Tooltip scoiataelDescription = new Tooltip("Decides who starts the game");
        scoiataelDescription.setFont(new Font(15));
        Tooltip skelligeDescription = new Tooltip("Two random cards from the grave will be placed on the board at the start of third round");
        skelligeDescription.setFont(new Font(15));
        Tooltip.install(monstersTab, monstersDescription);
        Tooltip.install(nilfgaardianTab, nilfgaardianDescription);
        Tooltip.install(realmsTab, realmsDescription);
        Tooltip.install(scoiataelTab, scoiataelDescription);
        Tooltip.install(skelligeTab, skelligeDescription);
    }


    private void showBigCard(Card card) {
        deckInfoBox.setVisible(false);
        leaderShowCase.setVisible(false);
        if(cardInfoBox.getChildren().size() > 2) cardInfoBox.getChildren().remove(0);
        Rectangle bigCard = new CardGraphicalizer(card, 240, 370);
        bigCard.getStyleClass().add("bigCard");
        cardInfoBox.getChildren().add(0, bigCard);
        bigCardInfo.setText("");
        if(card.getAbility() != null) {
            bigCardInfo.setText(card.getAbility().getDescription());
        }
        cardInfoBox.setVisible(true);
    }


    private void loadDeck(Deck lastDeck) {
        for (Card card : lastDeck.getCards().keySet()) {
            addCardToDeckGrid(card, lastDeck.getCardCount(card));
            if(lastDeck.getCardCount(card) == card.getNumberOfCardInFaction()) {
                disableCardSelection(card);
            }
        }
        updateCardIndicators();
    }

    private void loadFaction(String factionName) {
        ArrayList<HBox> cardRows = PregameFXController.getAllCardsOfFaction(factionName);
        for (HBox row : cardRows) {
            allCardsGrid.getChildren().add(row);
            for (Node card : row.getChildren()) {
                addClickHandler(card, false);
            }
        }
        ArrayList<HBox> leaderRows = PregameFXController.getAllLeadersOfFaction(factionName);
        for (HBox row : leaderRows) {
            leaderShowCase.getChildren().add(row);
            for (Node card : row.getChildren()) {
                addClickHandler(card, false);
            }
        }
    }


    private void addClickHandler(Node node, boolean isInDeck) {
        if(!(node instanceof CardGraphicalizer)) return;

        CardGraphicalizer card = (CardGraphicalizer) node;
        card.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.SECONDARY) {
                showBigCard(card.getCard());
            }
            if(event.getButton() == MouseButton.PRIMARY) {
                if(card.getCard() instanceof CommanderCard) {
                    selectLeader(card.getCard());
                } else {
                    if(!isInDeck) addToDeck(card.getCard());
                    else removeFromDeck(card.getCard());
                }
            }
        });
    }

    private void removeFromDeck(Card card) {
        int count = PregameFXController.countCardInDeck(card);
        updateDeckGrid(card, count - 1);
        if(count == card.getNumberOfCardInFaction()) {
            enableCardSelection(card);
        }
        PregameFXController.removeCardFromDeck(card);
        count--;
        if(count == 0) {
            removeCardFromDeckGrid(card);
        }
        updateCardIndicators();
    }

    private void updateCardIndicators() {
        int unitCount = PregameFXController.countUnitCards();
        int specialCount = PregameFXController.countSpecialCards();
        unitCardIndicator.setText("unit cards: " + unitCount + " out of 22");
        specialCardIndicator.setText("special cards: " + specialCount + " out of 10");

        if(unitCount >= 22) unitCardIndicator.setTextFill(new Color(0.30196078431372547d, 1, 0.48627450980392156d, 1));
        else unitCardIndicator.setTextFill(new Color(1, 0.45098039215686275d, 0.45098039215686275d, 1));

        if(specialCount <= 10) specialCardIndicator.setTextFill(new Color(0.30196078431372547d, 1, 0.48627450980392156d, 1));
        else specialCardIndicator.setTextFill(new Color(1, 0.45098039215686275d, 0.45098039215686275d, 1));

        updateEnableStatusOfConfirmButton(unitCount, specialCount);
    }
    
    private void updateEnableStatusOfConfirmButton(int unitCount, int specialCount) {
        if(PregameFXController.isCommanderSelected()) {
            if(unitCount >= 22 && specialCount <= 10) {
                confirmButton.setDisable(false);
                saveDeckButton.setDisable(false);
            } else {
                confirmButton.setDisable(true);
                saveDeckButton.setDisable(true);
            }
        } else {
            confirmButton.setDisable(true);
            saveDeckButton.setDisable(true);
        }
    }

    private void removeCardFromDeckGrid(Card card) {
        ArrayList<VBox> allCards = new ArrayList<>();
        for (Node rowNode : deckGrid.getChildren()) {
            for (Node cardBoxNode : ((HBox) rowNode).getChildren()) {
                CardGraphicalizer cardImage = (CardGraphicalizer) ((VBox) cardBoxNode).getChildren().get(0);
                if(!cardImage.getCard().equals(card)) {
                    allCards.add((VBox) cardBoxNode);
                }
            }
        }
        deckGrid.getChildren().clear();
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER);
        row.setSpacing(4);
        for (VBox cardBox : allCards) {
            row.getChildren().add(cardBox);
            if(row.getChildren().size() == 3) {
                deckGrid.getChildren().add(row);
                row = new HBox();
                row.setAlignment(Pos.CENTER);
                row.setSpacing(4);
            }
        }
        if(row.getChildren().size() > 0) {
            deckGrid.getChildren().add(row);
        }
    }

    private void enableCardSelection(Card card) {
        for (Node cardRowNode : allCardsGrid.getChildren()) {
            for (Node cardNode : ((HBox) cardRowNode).getChildren()) {
                if(((CardGraphicalizer) cardNode).getCard().equals(card)) {
                    cardNode.getStyleClass().remove("disabledCard");
                    return;
                }
            }
        }
    }

    private void addToDeck(Card card) {
        int cardCount = PregameFXController.countCardInDeck(card);
        if(cardCount == 0) {
            addCardToDeckGrid(card, 1);
            PregameFXController.addCardToDeck(card);
            cardCount++;
        } else {
            if(cardCount == card.getNumberOfCardInFaction()) return;

            PregameFXController.addCardToDeck(card);
            cardCount++;
            updateDeckGrid(card, cardCount);
        }
        if(cardCount == card.getNumberOfCardInFaction()) {
            disableCardSelection(card);
        }
        updateCardIndicators();
    }

    private void disableCardSelection(Card card) {
        for (Node cardRowNode : allCardsGrid.getChildren()) {
            for (Node cardNode : ((HBox) cardRowNode).getChildren()) {
                if(((CardGraphicalizer) cardNode).getCard().equals(card)) {
                    cardNode.getStyleClass().add("disabledCard");
                    return;
                }
            }
        }
    }

    private void updateDeckGrid(Card card, int newCount) {
        for (Node rowNode : deckGrid.getChildren()) {
            for (Node cardBoxNode : ((HBox) rowNode).getChildren()) {
                CardGraphicalizer cardImage = (CardGraphicalizer) ((VBox) cardBoxNode).getChildren().get(0);
                if(cardImage.getCard().equals(card)) {
                    ((Label) ((VBox) cardBoxNode).getChildren().get(1)).setText("#" + newCount);
                    return;
                }
            }
        }
    }

    private void addCardToDeckGrid(Card card, int count) {
        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.getStyleClass().add("cardAndCountContainer");
        container.setSpacing(3);

        CardGraphicalizer cardImage = new CardGraphicalizer(card, 120, 200);
        cardImage.getStyleClass().add("card");
        addClickHandler(cardImage, true);

        container.getChildren().addAll(cardImage, new Label("#" + count));

        if(deckGrid.getChildren().isEmpty()) {
            HBox row = new HBox();
            row.setAlignment(Pos.CENTER);
            row.setSpacing(4);
            row.getChildren().add(container);
            deckGrid.getChildren().add(row);
        } else {
            HBox lastCardRow = (HBox) deckGrid.getChildren().get(deckGrid.getChildren().size() - 1);
            if(lastCardRow.getChildren().size() < 3) {
                lastCardRow.getChildren().add(container);
            } else {
                HBox row = new HBox();
                row.setAlignment(Pos.CENTER);
                row.setSpacing(4);
                row.getChildren().add(container);
                deckGrid.getChildren().add(row);
            }
        }
    }

    private void selectLeader(Card card) {
        PregameFXController.setLeader(card);
        for (Node leaderRow : leaderShowCase.getChildren()) {
            for (Node leader : ((HBox) leaderRow).getChildren()) {
                leader.setId("");
                if(((CardGraphicalizer) leader).getCard().equals(card)) {
                    leader.setId("selectedLeader");
                }
            }
        }
        chooseLeaderButton.setText("leader: " + card.getName());
        updateEnableStatusOfConfirmButton(PregameFXController.countUnitCards(), PregameFXController.countSpecialCards());
        showDeckInfo();
    }

    @FXML
    private void loadFaction(MouseEvent event) {
        HBox factionTab = (HBox) event.getSource();
        if(factionTab.getId().equals("selected")) {
            return;
        }
        String factionName = ((Label) factionTab.getChildren().get(0)).getText();

        clearDeck(factionName);

        allCardsGrid.getChildren().clear();
        leaderShowCase.getChildren().clear();

        chooseLeaderButton.setText("choose your leader");

        loadFaction(factionName);
        
        resetSelectedTab();
        factionTab.setId("selected");
    }

    private void clearDeck(String factionName) {
        PregameFXController.clearPlayerDeck(factionName);
        deckGrid.getChildren().clear();
        unitCardIndicator.setText("unit cards: 0 out of 22");
        specialCardIndicator.setText("special cards: 0 out of 10");
        unitCardIndicator.setTextFill(new Color(1, 0.45098039215686275d, 0.45098039215686275d, 1));
        specialCardIndicator.setTextFill(new Color(0.30196078431372547d, 1, 0.48627450980392156d, 1));
        confirmButton.setDisable(true);
        saveDeckButton.setDisable(true);
    }

    private void resetSelectedTab() {
        monstersTab.setId("");
        nilfgaardianTab.setId("");
        realmsTab.setId("");
        scoiataelTab.setId("");
        skelligeTab.setId("");
    }


    @FXML
    private void goBack() {
        CardGraphicalizer bigCard = (CardGraphicalizer) cardInfoBox.getChildren().get(0);
        if(bigCard.getCard() instanceof CommanderCard ) {
            showLeaderShowcase();
        } else showDeckInfo();
    }

    private void showDeckInfo() {
        leaderShowCase.setVisible(false);
        cardInfoBox.setVisible(false);
        deckInfoBox.setVisible(true);
    }
    
    @FXML
    private void showLeaderShowcase() {
        cardInfoBox.setVisible(false);
        deckInfoBox.setVisible(false);
        leaderShowCase.setVisible(true);
    }


    @FXML
    private void confirmDeck() throws IOException {
        PregameFXController.confirmDeck();
    }
}