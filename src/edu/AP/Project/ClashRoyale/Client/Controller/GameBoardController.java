package edu.AP.Project.ClashRoyale.Client.Controller;
import edu.AP.Project.ClashRoyale.Client.Client;
import javafx.fxml.FXML;
        import javafx.scene.control.Label;
        import javafx.scene.control.ProgressBar;
        import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameBoardController {

    private Client client;

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
