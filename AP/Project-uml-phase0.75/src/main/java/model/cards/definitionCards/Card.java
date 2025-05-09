package model.cards.definitionCards;

import javafx.scene.shape.Rectangle;
import model.abilities.Ability;
import model.abilities.NullAbility;
import model.game.Player;
import model.game.Row;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Card {
    protected Ability ability;
    protected int power = -1;
    protected int originalPower = -1;
    private String name;
    private String transformName;
    protected int beforeTransformPower;

    protected final String backgroundAddress;

    public String getTransformName() {
        return transformName;
    }

    public void setTransformName(String transformName) {
        this.transformName = transformName;
    }

//    private final double REGULAR_WIDTH;
//    private final double REGULAR_HEIGHT;
//    private final double GAME_WIDTH;
//    private final double GAME_HEIGHT;
    private boolean commander;
    private boolean hero;
    private boolean special;
    private boolean unit;
    private Row row;
    private boolean affectedByWeather = false;
    boolean spell;
    private ArrayList<Integer> acceptableRows;
    //1: melee(front), 2:ranged(middle), 3:siege(back)

    private String factionName;
    private int numberOfCardInFaction;
    public Card(String factionName,int numberOfCardInFaction, String name, boolean commander, boolean special, boolean hero,
                boolean unit, boolean spell, ArrayList<Integer> acceptableRows, String backgroundAddress, Ability ability) {
        this.name = name;
        this.commander = commander;
        this.special = special;
        this.acceptableRows = acceptableRows;
        this.hero = hero;
        this.unit = unit;
        this.spell = spell;
        this.factionName = factionName;
        this.numberOfCardInFaction = numberOfCardInFaction;
        this.backgroundAddress = backgroundAddress;
        this.ability = ability;
    }
    public abstract Card clone(boolean originalPower);

    public void doAbility(Player turnPlayer, Player enemyPlayer){
        if (ability == null)
            ability = new NullAbility();
        ability.doAbility(this, turnPlayer, enemyPlayer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCommander(){
        return commander;
    }

    public ArrayList<Integer> getAcceptableRows() {
        return acceptableRows;
    }

    public boolean isSpecial(){
        return this.special;
    }

    public boolean isSpell() {
        return spell;
    }

    public boolean hasAbility(){
        return (this.ability != null);
    }

    public boolean hasPower(){
        return (this.power != -1);
    }

//    public double getREGULAR_WIDTH() {
//        return REGULAR_WIDTH;
//    }
//
//    public double getREGULAR_HEIGHT() {
//        return REGULAR_HEIGHT;
//    }
//
//    public double getGAME_WIDTH() {
//        return GAME_WIDTH;
//    }
//
//    public double getGAME_HEIGHT() {
//        return GAME_HEIGHT;
//    }

    public String getImageAddress() {
        return backgroundAddress;
    }

    public boolean isHero() {
        return hero;
    }

    public boolean isUnit() {
        return unit;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Row getRow() {
        return row;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
        //TODO
    }

    public Ability getAbility() {
        if (ability == null)
            this.ability = new NullAbility();
        return this.ability;
    }

    public void setPowerToOriginal(){
        setPower(originalPower);
    }

    public boolean isAffectedByWeather() {
        return affectedByWeather;
    }

    public void setAffectedByWeather(boolean affectedByWeather) {
        this.affectedByWeather = affectedByWeather;
    }

    public void kill(Player player, int rowNum){
        player.addToDiscardPile(this);
        player.getRows().get(rowNum - 1).getCards().remove(this);
        setRow(null);
    }

    public void neutralizeAbility() {
        ability = new NullAbility();
    }

    public void doublePower() {
        setPower(power * 2);
    }

    public static Card getCardWithName(String name) {
        for (Cards card : Cards.values()) {
            if (card.getCard().getName().equals(name)) return card.getCard();
        }
        return null;
    }

    public String getFactionName() {
        return this.factionName;
    }

    public int getNumberOfCardInFaction() {
        return this.numberOfCardInFaction;
    }

    public int getOriginalPower() {
        return originalPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public int getBeforeTransformPower() {
        return beforeTransformPower;
    }

    public void setOriginalPower(int originalPower) {
        this.originalPower = originalPower;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
