package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Models.CardModel;
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

import java.util.ArrayList;
import java.util.Objects;

import static edu.AP.Project.ClashRoyale.Client.Main.changeScene;
import static edu.AP.Project.ClashRoyale.Client.Main.getUsername;

public class ProfileController {

    private int counter = 0;

    @FXML
    private ImageView battleImage;

    @FXML
    private ImageView battleDeckImage;

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
    private ImageView changeUsernameImage;

    @FXML
    private JFXButton changeUsernameBtn;

    @FXML
    private ImageView changePasswordImage;

    @FXML
    private JFXButton changePasswordBtn;

    @FXML
    private ImageView signOutImage;

    @FXML
    private JFXButton signOutBtn;

    @FXML
    private HBox cardDeck;

    @FXML
    private Label username;

    @FXML
    private Label league;

    @FXML
    private Label cup;

    @FXML
    private void initialize(){
        username.setText(getUsername());
//        TODO get League and Cup
        league.setText("Bronze1");
        cup.setText("100");

        ArrayList<CardModel> cardModels = new ArrayList<>();
        cardModels.add(new CardModel(true , 1, "../Images/Cards/Troops/archers.png"));
        cardModels.add(new CardModel(true , 1, "../Images/Cards/Troops/baby_dragon.png"));
        cardModels.add(new CardModel(false , 1, "../Images/Cards/Troops/barbarians.png"));
        cardModels.add(new CardModel(true , 1, "../Images/Cards/Troops/giant.png"));
        cardModels.add(new CardModel(false , 2, "../Images/Cards/Troops/mini_pekka.png"));
        cardModels.add(new CardModel(true , 1, "../Images/Cards/Troops/valkyrie.png"));
        cardModels.add(new CardModel(false , 1, "../Images/Cards/Troops/wizard.png"));
        cardModels.add(new CardModel(true , 1, "../Images/Cards/spells/arrows.png"));
        cardModels.add(new CardModel(true , 1, "../Images/Cards/spells/fireball.png"));
        cardModels.add(new CardModel(true , 1, "../Images/Cards/spells/rage.png"));
        cardModels.add(new CardModel(false , 1, "../Images/Cards/buildings/cannon.png"));
        cardModels.add(new CardModel(true , 1, "../Images/Cards/buildings/inferno.png"));

        for (CardModel card: cardModels){
            if (card.isInDeck()){
                addCardToDeck(card);
            }
        }
    }
    private void addCardToDeck(CardModel card){
        VBox newObject = creatCardView(card);
        cardDeck.getChildren().add(counter,newObject);
        counter++;

    }

    private VBox creatCardView(CardModel card){
        VBox vBox = new VBox();
        vBox.alignmentProperty().set(Pos.CENTER);
//        Image card1 = new Image (Objects.requireNonNull(getClass().getResourceAsStream(address)));
        ImageView card1View = new ImageView();
        card1View.setPreserveRatio(true);
        card1View.setFitWidth(100);
        card1View.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(card.getCardImageAddress()))));
        Label levelTxt = new Label();
        levelTxt.setText("Level " + card.getLevel());
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
        changeScene("Views/BattleDeck.fxml");
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
    void mouseClicked(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));

        battleImage.setImage(silverButtonImage);
    }

    @FXML
    void mouseEntered(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        Image goldButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gold.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(silverButtonImage);
        if (event.getSource().equals(changeUsernameBtn))
            changeUsernameImage.setImage(goldButtonImage);
        if (event.getSource().equals(changePasswordBtn))
            changePasswordImage.setImage(goldButtonImage);
        if (event.getSource().equals(signOutBtn))
            signOutImage.setImage(goldButtonImage);
    }

    @FXML
    void mouseExited(MouseEvent event) {
        Image grayButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gray.png")));
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(grayButtonImage);
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(grayButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(grayButtonImage);
        if (event.getSource().equals(changeUsernameBtn))
            changeUsernameImage.setImage(silverButtonImage);
        if (event.getSource().equals(changePasswordBtn))
            changePasswordImage.setImage(silverButtonImage);
        if (event.getSource().equals(signOutBtn))
            signOutImage.setImage(silverButtonImage);
    }

    @FXML
    void signOut(ActionEvent event) {
        changeScene("Views/login.fxml");
    }


}
