package edu.AP.Project.ClashRoyale.Client.Controller;

import edu.AP.Project.ClashRoyale.Client.Client;
import edu.AP.Project.ClashRoyale.Client.Models.BoardModel;
import edu.AP.Project.ClashRoyale.Client.Views.BoardView;
import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.Timer;
import java.util.TimerTask;

public class BoardController {
    final private static double FRAMES_PER_SECOND = 5.0;
    Client client;
    public BoardController(Client client){
        this.client = client;
    }
    private BoardModel boardModel;
    private Timer timer;

    @FXML
    private BoardView board;

    public void initialize(){
        boardModel =new BoardModel();
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

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    private void update(){
        this.boardModel.update();
    }
}
