package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Models.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.Objects;

import static edu.AP.Project.ClashRoyale.Client.Main.changeScene;

public class BattleHistoryController {

    ObservableList<Game> games = FXCollections.observableArrayList();


    @FXML
    private ImageView battleImage;

    @FXML
    private ImageView battleDeckImage;

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
    private ListView<Game> listView;

    @FXML
    void battleClick(ActionEvent event) {
        changeScene("./Views/Battle.fxml");
    }

    @FXML
    void initialize(){
        // TODO get from server
        games.add(new Game(true,"Hamidreza" , 2,1,200));
        games.add(new Game(false,"Sobhan" , 0,3,70));
        games.add(new Game(false,"Abedi" , 2,3,70));
        games.add(new Game(true,"hamid" , 3,2,200));
        games.add(new Game(false,"Abooei" , 1,2,70));
        games.add(new Game(true,"Hamidreza" , 2,1,200));
        games.add(new Game(false,"Sobhan" , 0,3,70));
        games.add(new Game(false,"Abedi" , 2,3,70));
        games.add(new Game(true,"hamid" , 3,2,200));
        games.add(new Game(false,"Abooei" , 1,2,70));
        games.add(new Game(true,"Hamidreza" , 2,1,200));
        games.add(new Game(false,"Sobhan" , 0,3,70));
        games.add(new Game(false,"Abedi" , 2,3,70));
        games.add(new Game(true,"hamid" , 3,2,200));
        games.add(new Game(false,"Abooei" , 1,2,70));
        games.add(new Game(true,"Hamidreza" , 2,1,200));
        games.add(new Game(false,"Sobhan" , 0,3,70));
        games.add(new Game(false,"Abedi" , 2,3,70));
        games.add(new Game(true,"hamid" , 3,2,200));
        games.add(new Game(false,"Abooei" , 1,2,70));

        listView.setItems(games);
        listView.setCellFactory(
            new Callback<ListView<Game>, ListCell<Game>>() {
                @Override
                public ListCell<Game> call(ListView<Game> listView) {
                    return new BattleGameCellViewerController();
                }
            }
        );
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
        changeScene("Views/profile.fxml");

    }

    @FXML
    void mouseClicked(MouseEvent event) {

    }

    @FXML
    void mouseEntered(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(silverButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(silverButtonImage);
    }

    @FXML
    void mouseExited(MouseEvent event) {
        Image grayButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gray.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(grayButtonImage);
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(grayButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(grayButtonImage);

    }

}
