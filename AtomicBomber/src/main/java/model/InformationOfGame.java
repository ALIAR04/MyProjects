package model;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class InformationOfGame {
    private int numberOfClusters = 2;
    private int hitPoint = 3;
    private int kills = 0;
    private int numberOfKills = 0;
    private int numberOfShoots = 0;
    private String nameOfWave;
    private Label labelKills;
    private Rectangle imageHitPoint1;
    private Rectangle imageHitPoint2;
    private Rectangle imageHitPoint3;
    private Label labelClusters;

    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    public void setNumberOfClusters(int numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void setNumberOfKills(int numberOfKills) {
        this.numberOfKills = numberOfKills;
    }

    public int getNumberOfShoots() {
        return numberOfShoots;
    }

    public void setNumberOfShoots(int numberOfShoots) {
        this.numberOfShoots = numberOfShoots;
    }

    public String getNameOfWave() {
        return nameOfWave;
    }

    public void setNameOfWave(String nameOfWave) {
        this.nameOfWave = nameOfWave;
    }

    public Label createKillNumber(Pane pane, User user) {
        Label label = new Label();
        label.setText("kills: " + String.valueOf(user.getMainMenuMain().getInformationOfGame().getKills()));
        label.setLayoutX(1000);
        label.setLayoutY(20);
        pane.getChildren().add(label);
        labelKills = label;
        return label;
    }

    public Label getLabelKills() {
        return labelKills;
    }

    public void setLabelKills(Label labelKills) {
        this.labelKills = labelKills;
    }

    public Rectangle getImageHitPoint1() {
        return imageHitPoint1;
    }

    public void setImageHitPoint1(Rectangle imageHitPoint1) {
        this.imageHitPoint1 = imageHitPoint1;
    }

    public Rectangle getImageHitPoint2() {
        return imageHitPoint2;
    }

    public void setImageHitPoint2(Rectangle imageHitPoint2) {
        this.imageHitPoint2 = imageHitPoint2;
    }

    public Rectangle getImageHitPoint3() {
        return imageHitPoint3;
    }

    public void setImageHitPoint3(Rectangle imageHitPoint3) {
        this.imageHitPoint3 = imageHitPoint3;
    }

    public Rectangle createHitPoint1(Pane pane, User currentUser) {
        Rectangle rectangle = new Rectangle(25,35);
        Image image = new Image(InformationOfGame.class.getResource("/Images/heart.png").toExternalForm());
        ImagePattern imagePattern = new ImagePattern(image);
        rectangle.setX(1000);
        rectangle.setY(50);
        rectangle.setFill(imagePattern);
        this.imageHitPoint1 = rectangle;
        return rectangle;
    }

    public Rectangle createHitPoint2(Pane pane, User currentUser) {
        Rectangle rectangle = new Rectangle(25,35);
        Image image = new Image(InformationOfGame.class.getResource("/Images/heart.png").toExternalForm());
        ImagePattern imagePattern = new ImagePattern(image);
        rectangle.setX(1030);
        rectangle.setY(50);
        rectangle.setFill(imagePattern);
        this.imageHitPoint2 = rectangle;
        return rectangle;
    }

    public Rectangle createHitPoint3(Pane pane, User currentUser) {
        Rectangle rectangle = new Rectangle(25,35);
        Image image = new Image(InformationOfGame.class.getResource("/Images/heart.png").toExternalForm());
        ImagePattern imagePattern = new ImagePattern(image);
        rectangle.setX(1060);
        rectangle.setY(50);
        rectangle.setFill(imagePattern);
        this.imageHitPoint3 = rectangle;
        return rectangle;
    }

    public Rectangle createClusterImage() {
        Rectangle rectangle = new Rectangle(30,35);
        Image image = new Image(InformationOfGame.class.getResource("/Images/cluster.png").toExternalForm());
        ImagePattern imagePattern = new ImagePattern(image);
        rectangle.setX(1060);
        rectangle.setY(100);
        rectangle.setFill(imagePattern);
        return rectangle;
    }

    public Label getLabelClusters() {
        return labelClusters;
    }

    public void setLabelClusters(Label labelClusters) {
        this.labelClusters = labelClusters;
    }

    public Label createClusterLabel(Pane pane, User currentUser) {
        Label label = new Label();
        label.setText(String.valueOf(numberOfClusters));
        label.setLayoutX(1040);
        label.setLayoutY(100);

        return label;
    }
}
