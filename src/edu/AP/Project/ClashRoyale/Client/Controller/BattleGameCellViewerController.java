package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Client;
import edu.AP.Project.ClashRoyale.Client.Models.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

public class BattleGameCellViewerController extends ListCell<Game> {

    private Client client;

    /**
     * constructor
     * @param client
     */
    public BattleGameCellViewerController(Client client){
        this.client = client;
    }

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView loseWin;

    @FXML
    private Label enemyUsername;

    @FXML
    private Label clientCups;

    @FXML
    private Label enemyCups;

    @FXML
    private Label score;

    @FXML
    private ImageView viewImage;

    @FXML
    private JFXButton viewBtn;

    private FXMLLoader mLLoader;

    /**
     * update Item
     * @param item game
     * @param empty is empty or not
     */
    protected void updateItem(Game item, boolean empty) {

        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null); // don't display anything
        }
        else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../Views/BattleGameCellViewer.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            loseWin.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImageAddress()))));
            enemyUsername.setText(item.getEnemyUsername());
            clientCups.setText(String.valueOf(item.getClientCups()));
            enemyCups.setText(String.valueOf(item.getEnemyCups()));
            score.setText(String.valueOf(item.getScore()));
            setGraphic(gridPane);
        }
    }

    /**
     * mouse entered to change Image
     * @param event mouse event
     */
    @FXML
    void mouseEntered(MouseEvent event) {
        Image goldButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gold.png")));
        viewImage.setImage(goldButtonImage);
    }

    /**
     * mouse exited change button image
     * @param event Mouse event
     */
    @FXML
    void mouseExited(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        viewImage.setImage(silverButtonImage);
    }

    /**
     * view clicked
     * @param event action event
     */
    @FXML
    void viewClicked(ActionEvent event) {

    }


}
