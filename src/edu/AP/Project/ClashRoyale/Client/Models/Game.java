package edu.AP.Project.ClashRoyale.Client.Models;

import javafx.scene.image.Image;

import java.util.Objects;

public class Game {
    private boolean win;
    private String enemyUsername;
    private int clientCups;
    private int enemyCups;
    private int score;
    private final String winImagePath = "../Images/check.png";
    private final String loseImagePath = "../Images/x.png";

    /**
     * new game Constructor
     * @param win win or loose
     * @param enemyUsername enemy Username
     * @param clientCups client Cups
     * @param enemyCups enemy Cups
     * @param score score earned
     */
    public Game(boolean win, String enemyUsername, int clientCups, int enemyCups, int score) {
        this.win = win;
        this.enemyUsername = enemyUsername;
        this.clientCups = clientCups;
        this.enemyCups = enemyCups;
        this.score = score;
    }

    /**
     * get win/loose game
     * @return address of image
     */
    public String getImageAddress(){
        String address;
        if (win){
            address = winImagePath;
        }else{
            address = loseImagePath;
        }
        return address;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * get enemy username
     * @return name of enemy
     */
    public String getEnemyUsername() {
        return enemyUsername;
    }

    public void setEnemyUsername(String enemyUsername) {
        this.enemyUsername = enemyUsername;
    }

    /**
     *
     * @return the client cups earned in this game
     */
    public int getClientCups() {
        return clientCups;
    }

    public void setClientCups(int clientCups) {
        this.clientCups = clientCups;
    }

    /**
     *
     * @returnthe enemy cups earned in this game
     */
    public int getEnemyCups() {
        return enemyCups;
    }

    public void setEnemyCups(int enemyCups) {
        this.enemyCups = enemyCups;
    }

    /**
     *
     * @return scores earned
     */
    public int getScore() {
        return score;
    }

    /**
     * set score
     * @param score score earned
     */
    public void setScore(int score) {
        this.score = score;
    }
}
