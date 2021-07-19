package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.Objects;

import static edu.AP.Project.ClashRoyale.Client.Main.changeScene;

public class BattleDeckController {
    private int deckCardNumber = 8;
    private int numberPerHBox = 4;
    private int deckCounter = 0;
    private int collectionCounter = 0;


    @FXML
    private ImageView battleImage;

    @FXML
    private ImageView battleHistoryImage;

    @FXML
    private ImageView profileImage;

    @FXML
    private JFXButton battleBtn;

    @FXML
    private JFXButton battleDeckBtn;

    @FXML
    private JFXButton battleHistory;

    @FXML
    private JFXButton profileBtn;

    @FXML
    private VBox battleDeckVBox;

    @FXML
    private HBox deckHBox1;

    @FXML
    private HBox deckHBox2;

    @FXML
    private VBox battleCollectionVBox;

    @FXML
    private HBox collectionHBox1;

    @FXML
    private HBox collectionHBox2;

    @FXML
    void initialize(){
//        TODO Request server for battleDeck Cards and their Level
//        VBo
        addCardToDeck("../Images/Cards/Troops/archers.png" ,4);
        addCardToDeck("../Images/Cards/Troops/baby_dragon.png" , 2);
        addCardToCollection("../Images/Cards/Troops/barbarians.png", 1);
        addCardToDeck("../Images/Cards/Troops/giant.png", 1);
        addCardToDeck("../Images/Cards/Troops/mini_pekka.png", 1);
        addCardToCollection("../Images/Cards/Troops/valkyrie.png", 3);
        addCardToDeck("../Images/Cards/Troops/wizard.png", 1);
        addCardToDeck("../Images/Cards/spells/arrows.png", 2);
        addCardToCollection("../Images/Cards/spells/fireball.png", 2);
        addCardToDeck("../Images/Cards/spells/rage.png", 1);
        addCardToDeck("../Images/Cards/buildings/cannon.png", 2);
        addCardToCollection("../Images/Cards/buildings/inferno.png", 1);

    }
    private void addCardToDeck(String address,int level){
        VBox newObject = creatCardView(address , level);
        if(deckCounter < numberPerHBox){
            deckHBox1.getChildren().add(deckCounter,newObject);
            deckCounter++;
        }else{
            deckHBox2.getChildren().add(deckCounter-numberPerHBox , newObject);
            deckCounter++;
        }

    }

    private void addCardToCollection(String address , int level){
        VBox newObject = creatCardView(address , level);
        if(collectionCounter < numberPerHBox){
            collectionHBox1.getChildren().add(collectionCounter,newObject);
            collectionCounter++;
        }else{
            collectionHBox2.getChildren().add(collectionCounter-numberPerHBox , newObject);
            collectionCounter++;
        }
    }

    private VBox creatCardView(String address, int level){
        VBox vBox = new VBox();
        vBox.alignmentProperty().set(Pos.CENTER);
        Image card1 = new Image (Objects.requireNonNull(getClass().getResourceAsStream(address)));
        ImageView card1View = new ImageView();
        card1View.setPreserveRatio(true);
        card1View.setFitWidth(100);
        card1View.setImage(card1);
        Label levelTxt = new Label();
        levelTxt.setText("Level " + level);
        levelTxt.setFont(Font.font("Lilita One",20));
        levelTxt.textFillProperty().set(Paint.valueOf("#b5b2ff"));
        vBox.getChildren().addAll(card1View,levelTxt);
        return vBox;
    }

    @FXML
    void battleClick(ActionEvent event) {
        changeScene("./Views/Battle.fxml");
    }

    @FXML
    void battleDeckClick(ActionEvent event) {

//        Image image = new Image();
//        hbox.
    }

    @FXML
    void battleHistoryClick(ActionEvent event) {
        changeScene("Views/BattleHistory.fxml");
    }

    @FXML
    void profileClick(ActionEvent event) {
        changeScene("Views/Profile.fxml");
    }

    @FXML
    void mouseEntered(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(silverButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(silverButtonImage);
    }

    @FXML
    void mouseExited(MouseEvent event) {
        Image grayButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gray.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(grayButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(grayButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(grayButtonImage);

    }

}
