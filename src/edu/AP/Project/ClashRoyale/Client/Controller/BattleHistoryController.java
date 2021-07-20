package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Client;
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
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.Objects;



public class BattleHistoryController {

    private ObservableList<Game> games = FXCollections.observableArrayList();
    private Client client;

    public BattleHistoryController(Client client){
        this.client = client;
    }

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
        client.changeScene("./Views/Battle.fxml" , new BattleController(client));
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
                    return new BattleGameCellViewerController(client);
                }
            }
        );
    }



    @FXML
    void battleDeckClick(ActionEvent event) {
        client.changeScene("Views/BattleDeck.fxml" , new BattleDeckController(client));

    }

    @FXML
    void battleHistoryClick(ActionEvent event) {
        client.changeScene("Views/BattleHistory.fxml" , new BattleHistoryController(client));

    }

    @FXML
    void profileClick(ActionEvent event) {
        client.changeScene("Views/profile.fxml" , new ProfileController(client));

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
