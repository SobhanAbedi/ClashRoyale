package edu.AP.Project.ClashRoyale.Client.Controller;

import edu.AP.Project.ClashRoyale.Client.Client;
import edu.AP.Project.ClashRoyale.Client.Models.BoardModel;
import edu.AP.Project.ClashRoyale.Client.Views.BoardView;
import edu.AP.Project.ClashRoyale.Model.GlobalVariables;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.util.Timer;
import java.util.TimerTask;

public class BoardController  implements EventHandler<KeyEvent> {
    final private static double FRAMES_PER_SECOND = 5.0;
    Client client;

    @Override
    public void handle(KeyEvent keyEvent) {
        keyEvent.getCode();
        keyEvent.consume();
    }

    /**
     * constructor
     * @param client client to reach common data
     */
    public BoardController(Client client) {
        this.client = client;
    }

    private BoardModel boardModel;
    private Timer timer;

    @FXML
    private BoardView board;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label gameOverLabel;

    @FXML
    private Label levelLabel;

    /**
     * initialize board Model
     */
    public void initialize(){

        boardModel = new BoardModel();
        update();
        startTimer();
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
                        update();
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 * GlobalVariables.DELTA_TIME);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    /**
     * Update View
     */
    private void update(){
        this.boardModel.update();
    }
}
