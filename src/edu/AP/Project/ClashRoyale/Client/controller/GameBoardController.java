package edu.ap.project.clashRoyale.client.controller;
import edu.ap.project.clashRoyale.client.Client;
import javafx.fxml.FXML;
        import javafx.scene.control.Label;
        import javafx.scene.control.ProgressBar;
        import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameBoardController {

    private Client client;

    /**
     * Game Board Controller constructor
     * @param client client to access commmon data
     */
    public GameBoardController(Client client){
        this.client = client;
    }

    @FXML
    private Label clientId;

    @FXML
    private Label enemyId;

    @FXML
    private Label timeLeftTxt;

    @FXML
    private ImageView elixirImage;

    @FXML
    private ProgressBar elixirAmount;


    @FXML
    private Pane gamePane;

}
