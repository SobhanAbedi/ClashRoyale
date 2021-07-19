package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.Objects;

import static edu.AP.Project.ClashRoyale.Client.Main.changeScene;

public class ProfileController {

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
    private HBox hbox;

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
