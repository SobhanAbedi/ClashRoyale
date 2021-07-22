package edu.ap.project.clashRoyale.client.controller;

import com.jfoenix.controls.JFXButton;
import edu.ap.project.clashRoyale.client.Client;
import edu.ap.project.clashRoyale.client.models.Game;
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

    /**
     * Constructor
     * @param client client to access common date
     */
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

    /**
     * Battle Menu
     * @param event action event
     */
    @FXML
    void battleClick(ActionEvent event) {
        client.changeScene("./Views/Battle.fxml" , new BattleController(client));
        client.buttonClickSound();
    }

    /**
     * initialize model and history model
     */
    @FXML
    void initialize(){
        // TODO get from server
        games.add(new Game(true,"Hamidreza" , 2,1,200));
        games.add(new Game(false,"Sobhan" , 0,3,70));
        games.add(new Game(false,"Abedi" , 2,3,70));
        games.add(new Game(true,"hamid" , 3,2,200));
        games.add(new Game(false,"Abooei" , 1,2,70));
//        games.add(new Game(true,"Hamidreza" , 2,1,200));
//        games.add(new Game(false,"Sobhan" , 0,3,70));
//        games.add(new Game(false,"Abedi" , 2,3,70));
//        games.add(new Game(true,"hamid" , 3,2,200));
//        games.add(new Game(false,"Abooei" , 1,2,70));
//        games.add(new Game(true,"Hamidreza" , 2,1,200));
//        games.add(new Game(false,"Sobhan" , 0,3,70));
//        games.add(new Game(false,"Abedi" , 2,3,70));
//        games.add(new Game(true,"hamid" , 3,2,200));
//        games.add(new Game(false,"Abooei" , 1,2,70));
//        games.add(new Game(true,"Hamidreza" , 2,1,200));
//        games.add(new Game(false,"Sobhan" , 0,3,70));
//        games.add(new Game(false,"Abedi" , 2,3,70));
//        games.add(new Game(true,"hamid" , 3,2,200));
//        games.add(new Game(false,"Abooei" , 1,2,70));

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


    /**
     * Battle Deck Menu
     * @param event action event
     */
    @FXML
    void battleDeckClick(ActionEvent event) {
        client.changeScene("Views/BattleDeck.fxml" , new BattleDeckController(client));
        client.buttonClickSound();
    }

    /**
     * Battle History Menu
     * @param event
     */
    @FXML
    void battleHistoryClick(ActionEvent event) {
        client.changeScene("Views/BattleHistory.fxml" , new BattleHistoryController(client));
        client.buttonClickSound();
    }

    /**
     * Profile Menu
     * @param event
     */
    @FXML
    void profileClick(ActionEvent event) {
        client.changeScene("Views/profile.fxml" , new ProfileController(client));
        client.buttonClickSound();
    }

    /**
     * mouseClicked
     * @param event Mouse Clicked
     */
    @FXML
    void mouseClicked(MouseEvent event) {
        client.buttonClickSound();
    }

    /**
     * mouse entered change button image
     * @param event Mouse event
     */
    @FXML
    void mouseEntered(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(silverButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(silverButtonImage);
        client.buttonEnterSound();
    }

    /**
     * mouse exited change button image
     * @param event Mouse event
     */
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
