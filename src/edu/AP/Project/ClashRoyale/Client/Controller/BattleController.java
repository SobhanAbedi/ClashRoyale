package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

import static edu.AP.Project.ClashRoyale.Client.Main.changeScene;


public class BattleController {


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
    private ImageView trainingImage;

    @FXML
    private ImageView trainingSmartImage;

    @FXML
    private ImageView oneImage;

    @FXML
    private ImageView twoImage;

    @FXML
    private JFXButton trainingBtn;

    @FXML
    private JFXButton trainingSmartBtn;

    @FXML
    private JFXButton oneBtn;

    @FXML
    private JFXButton twoBtn;

    @FXML
    private ImageView imageBox;

    @FXML
    void initialize(){
        Image arena = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/arenas/arena1.png")));
        imageBox.setImage(arena);
    }


    @FXML
    void battleClick(ActionEvent event) {

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
    void oneClick(ActionEvent event) {

    }

    @FXML
    void trainingClick(ActionEvent event) {

    }

    @FXML
    void trainingSmartClick(ActionEvent event) {

    }

    @FXML
    void twoClick(ActionEvent event) {

    }

    @FXML
    void mouseEntered(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(silverButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(silverButtonImage);
    }

    @FXML
    void mouseExited(MouseEvent event) {
        Image grayButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gray.png")));
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(grayButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(grayButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(grayButtonImage);

    }

    @FXML
    void longEntered(MouseEvent event) {
        Image goldButtonLongImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/goldSolong.png")));
        if (event.getSource().equals(trainingBtn))
            trainingImage.setImage(goldButtonLongImage);
        if (event.getSource().equals(trainingSmartBtn))
            trainingSmartImage.setImage(goldButtonLongImage);
        if (event.getSource().equals(oneBtn))
            oneImage.setImage(goldButtonLongImage);
        if (event.getSource().equals(twoBtn))
            twoImage.setImage(goldButtonLongImage);
    }

    @FXML
    void longExited(MouseEvent event) {
        Image silverButtonLongImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silverSolong.png")));
        if (event.getSource().equals(trainingBtn))
            trainingImage.setImage(silverButtonLongImage);
        if (event.getSource().equals(trainingSmartBtn))
            trainingSmartImage.setImage(silverButtonLongImage);
        if (event.getSource().equals(oneBtn))
            oneImage.setImage(silverButtonLongImage);
        if (event.getSource().equals(twoBtn))
            twoImage.setImage(silverButtonLongImage);
    }


}
