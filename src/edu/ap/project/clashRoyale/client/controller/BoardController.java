package edu.ap.project.clashRoyale.client.controller;

import edu.ap.project.clashRoyale.client.Client;
import edu.ap.project.clashRoyale.client.models.BoardModel;
import edu.ap.project.clashRoyale.client.views.BoardView;
import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PointDouble;
import edu.ap.project.clashRoyale.model.instructions.server.ServerInstruction;
import edu.ap.project.clashRoyale.model.instructions.server.ServerInstructionKind;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class BoardController {
    final private static double FRAMES_PER_SECOND = 5.0;
    private Client client;
    private BoardModel boardModel;
    private Timer timer;
    private final String cardRelativePath = "../images/Cards/";
    private int chosenCardPos;
    private ImageView[] deckViews;
    private final static double NOT_SELECTED_OPACITY = 0.8;
    private final static double NOT_AVAILABLE_OPACITY = 0.5;

    /**
     * constructor
     * @param client client to reach common data
     */
    public BoardController(Client client, BoardModel boardModel) {
        this.client = client;
        this.boardModel = boardModel;
        chosenCardPos = 0;
        deckViews = new ImageView[4];
    }

    @FXML
    private Label opponentName;

    @FXML
    private Label result;

    @FXML
    private Label timeRemaining;

    @FXML
    private ProgressBar elixirBar;

    @FXML
    private BoardView boardView;

    @FXML
    private ImageView deck1;

    @FXML
    private ImageView deck2;

    @FXML
    private ImageView deck3;

    @FXML
    private ImageView deck4;

    @FXML
    void deck1Click(MouseEvent event) {
        if(boardModel.getDeckCards(1).getCost() <= boardModel.getElixirRemaining().get()) {
            if (chosenCardPos != 1) {
                removeCardSelection();
                chosenCardPos = 1;
                deck1.setOpacity(1);
            } else {
                removeCardSelection();
            }
        }
        event.consume();
    }

    @FXML
    void deck2Click(MouseEvent event) {
        if(boardModel.getDeckCards(2).getCost() <= boardModel.getElixirRemaining().get()) {
            if (chosenCardPos != 2) {
                removeCardSelection();
                chosenCardPos = 2;
                deck2.setOpacity(1);
            } else {
                removeCardSelection();
            }
        }
        event.consume();
    }

    @FXML
    void deck3Click(MouseEvent event) {
        if(boardModel.getDeckCards(3).getCost() <= boardModel.getElixirRemaining().get()) {
            if (chosenCardPos != 3) {
                removeCardSelection();
                chosenCardPos = 3;
                deck3.setOpacity(1);
            } else {
                removeCardSelection();
            }
        }
        event.consume();
    }

    @FXML
    void deck4Click(MouseEvent event) {
        if(boardModel.getDeckCards(4).getCost() <= boardModel.getElixirRemaining().get()) {
            if (chosenCardPos != 4) {
                removeCardSelection();
                chosenCardPos = 4;
                deck4.setOpacity(1);
            } else {
                removeCardSelection();
            }
        }
        event.consume();
    }

    @FXML
    void checkMouseLocation(MouseEvent event) {
        if(chosenCardPos != 0) {
            //int x = (int)Math.floor(event.getX()/BoardView.CELL_WIDTH - 1);
            //int y = (int)Math.floor(((boardModel.getRowCount()*BoardView.CELL_WIDTH) - event.getY())/BoardView.CELL_WIDTH - 1);
            Card card = boardModel.getDeckCards(chosenCardPos);
            int x = (int)Math.floor(event.getX()/BoardView.CELL_WIDTH);
            int y = (int)Math.floor(event.getY()/BoardView.CELL_WIDTH);
            boardView.DrawBaseBoard(boardModel);
            if(!boardModel.canPlace(y, x, isSpell(card))) {
                boardView.setCellView(y, x, boardView.redRegionImage);
            } else {
                boardView.setCellView(y, x, boardView.whiteRegionImage);
            }
        }
        event.consume();
    }

    @FXML
    void placeCard(MouseEvent event) {
        if(chosenCardPos != 0) {
            Card card = boardModel.getDeckCards(chosenCardPos);
            int x = (int)Math.floor(event.getX()/BoardView.CELL_WIDTH);
            int y = (int)Math.floor(event.getY()/BoardView.CELL_WIDTH);
            boardView.DrawBaseBoard(boardModel);
            if(boardModel.canPlace(y, x, isSpell(card))) {
                PointDouble forceLocation = new PointDouble(0, 0);
                if(boardModel.getPlayerSide() == 1) {
                    forceLocation.setLocation(((GlobalVariables.QUARTER_WIDTH + 1) - x), (y - (GlobalVariables.QUARTER_LENGTH + 1)));
                } else {
                    forceLocation.setLocation((x - (GlobalVariables.QUARTER_WIDTH + 1)), ((GlobalVariables.QUARTER_LENGTH + 1) - y));
                }
                System.out.println("ForceLocation: " + forceLocation);
                Card pulledCard = boardModel.removeDeckCard(chosenCardPos);
                deckViews[chosenCardPos-1].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getCardPath(boardModel.getDeckCards(chosenCardPos).getName())))));
                deckViews[chosenCardPos-1].setOpacity(NOT_SELECTED_OPACITY);
                chosenCardPos = 0;
                boardModel.useElixir(pulledCard.getCost());
                client.getClientHandler().playCard(new ServerInstruction(ServerInstructionKind.PLAY_CARD, card, forceLocation, boardModel.getCurrentTime() + 1));
            }
        }
        event.consume();
    }

    /**
     * initialize board Model
     */
    public void initialize(){
        boardView.DrawBaseBoard(boardModel);
        boardView.setPlayerSide(boardModel.getPlayerSide());
        elixirBar.progressProperty().bind(boardModel.getElixirBarValue());
        deckViews[0] = deck1;
        deckViews[1] = deck2;
        deckViews[2] = deck3;
        deckViews[3] = deck4;
        for(int i = 0; i < 4; i++) {
            deckViews[i].setPreserveRatio(true);
            deckViews[i].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getCardPath(boardModel.getDeckCards(i + 1).getName())))));
        }
        removeCardSelection();
        opponentName.setText(boardModel.getOpponentName());
        result.setText("");
        startTimer();
    }

    public void removeCardSelection() {
        for(int i = 0; i < 4; i++)
            deckViews[i].setOpacity(NOT_SELECTED_OPACITY);
        chosenCardPos = 0;
    }

    /**
     * Schedules the model to update based on the timer.
     */
    private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        update(boardModel.getCurrentStep(), boardModel.getCurrentState());
                    }
                });
                boardModel.nextStep();
                for(int i = 1; i <= 4; i++) {
                    if(boardModel.getDeckCards(i).getCost() > boardModel.getElixirRemaining().get()) {
                        deckViews[i-1].setOpacity(NOT_AVAILABLE_OPACITY);
                    } else if(deckViews[i-1].getOpacity() == NOT_AVAILABLE_OPACITY) {
                        deckViews[i-1].setOpacity(NOT_SELECTED_OPACITY);
                    }
                }
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 * GlobalVariables.DELTA_TIME);
        this.timer.schedule(timerTask, 350, frameTimeInMilliseconds);
    }

    /**
     * Update View
     */
    private void update(int currentStep, ArrayList<ForceState> forces){
        for(ForceState force : forces) {
            switch (force.getActionKind()) {
                case CREATE:
                    boardView.addForce(force);
                    break;
                case MOVE:
                case ATTACK:
                    //boardView.updateForce(force);
                    break;
                case DIE:
                    boardView.removeForce(force.getForceID());
                    break;
                case DEAD:
                    boardView.makeSureIsDead(force.getForceID());
                    break;
            }
        }
        timeRemaining.setText(boardModel.gameTimer.getTimeRemaingString());
    }

    private String getCardPath(String cardName){
        return cardRelativePath + cardName.toLowerCase() +".png";
    }

    private boolean isSpell(Card card) {
        String name = card.getName();
        return (name.equals("Arrows") || name.equals("Fireball") || name.equals("Rage"));
    }
}
