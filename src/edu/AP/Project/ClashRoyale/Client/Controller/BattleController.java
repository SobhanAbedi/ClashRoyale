package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Clash Royale
 * @author Hamidreza Abooei & Sobhan Abedi
 */
public class BattleController {
    private Client client;
    public BattleController(Client client){
        this.client = client;
    }

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
    private Label cups;

    @FXML
    private Label coins;

    /**
     * initialize data for Battle Menu
     */
    @FXML
    void initialize(){
        Image arena ;
        switch (client.getPlayerInfo().getLevel()){
            case 2:
                arena = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/arenas/arena2.png")));
                break;
            case 3:
                arena = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/arenas/arena3.png")));
                break;
            case 4:
                arena = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/arenas/arena4.png")));
                break;
            case 5:
                arena = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/arenas/arena5.png")));
                break;
            default:
                arena = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/arenas/arena1.png")));
        }
        imageBox.setImage(arena);
        coins.setText(String.valueOf(client.getPlayerInfo().getCoins()));
        cups.setText(String.valueOf(client.getPlayerInfo().getScore()));

    }

    /**
     * Battle Menu
     * @param event action event
     */
    @FXML
    void battleClick(ActionEvent event) {
        client.changeScene("Views/Battle.fxml", new BattleDeckController(client));
        client.buttonClickSound();
    }

    /**
     * BattleDeck Menu
     * @param event action event
     */
    @FXML
    void battleDeckClick(ActionEvent event) {
        client.changeScene("Views/BattleDeck.fxml", new BattleDeckController(client));
        client.buttonClickSound();
    }

    /**
     * Battle History Menu
     * @param event action event
     */
    @FXML
    void battleHistoryClick(ActionEvent event) {
        client.changeScene("Views/BattleHistory.fxml", new BattleHistoryController(client));
        client.buttonClickSound();
    }

    /**
     * Profile Menu
     * @param event action event
     */
    @FXML
    void profileClick(ActionEvent event) {
        client.changeScene("Views/Profile.fxml" , new ProfileController(client));
        client.buttonClickSound();
    }

    /**
     * One By One Game
     * @param event
     */
    @FXML
    void oneClick(ActionEvent event) {
        client.buttonClickSound();
    }

    /**
     * training with Bot
     * @param event action event
     */
    @FXML
    void trainingClick(ActionEvent event) {
//        client.changeScene("Views/Board.fxml", new BoardController(client));
        client.changeScene("Views/GameBoard.fxml" , new GameBoardController(client));
        client.buttonClickSound();
    }

    /**
     * training with smart Bot
     * @param event action event
     */
    @FXML
    void trainingSmartClick(ActionEvent event) {
        client.buttonClickSound();
    }

    /**
     * Two by two Game
     * @param event action event
     */
    @FXML
    void twoClick(ActionEvent event) {

    }

    /**
     * mouse enter a button to change its image
     * @param event action event
     */
    @FXML
    void mouseEntered(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(silverButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(silverButtonImage);
        client.buttonEnterSound();
    }

    /**
     * mouse exited to turn back button image
     * @param event action event
     */
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

    /**
     * mouse enter a  long button to change its image
     * @param event action event
     */
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
        client.buttonEnterSound();
    }

    /**
     * mouse exited a  long button to change its image
     * @param event
     */
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
