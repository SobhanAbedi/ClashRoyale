package edu.AP.Project.ClashRoyale.Client.Controller;
import edu.AP.Project.ClashRoyale.Client.Client;
import edu.AP.Project.ClashRoyale.Client.Models.BoardModel;
import edu.AP.Project.ClashRoyale.Client.Views.BoardView;
import javafx.fxml.FXML;
import javafx.scene.Group;
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
    private Group gameBoard;

    @FXML
    private Pane gamePane;

    @FXML
    private void initialize(){
        BoardView boardView = new BoardView();
        BoardModel boardModel = new BoardModel();
        boardView.setRowCount(boardModel.getRowCount());
        boardView.setColumnCount(boardModel.getColumnCount());
        boardView.initializeGrid();
        boardView.update(boardModel);
        gamePane.getChildren().add(boardView);

//        gameBoard = boardView;
//        gameBoard.getChildren().addAll(boardView.getChildren());
    }

}
