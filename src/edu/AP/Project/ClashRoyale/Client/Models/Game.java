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


    public Game(boolean win, String enemyUsername, int clientCups, int enemyCups, int score) {
        this.win = win;
        this.enemyUsername = enemyUsername;
        this.clientCups = clientCups;
        this.enemyCups = enemyCups;
        this.score = score;
    }

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

    public String getEnemyUsername() {
        return enemyUsername;
    }

    public void setEnemyUsername(String enemyUsername) {
        this.enemyUsername = enemyUsername;
    }

    public int getClientCups() {
        return clientCups;
    }

    public void setClientCups(int clientCups) {
        this.clientCups = clientCups;
    }

    public int getEnemyCups() {
        return enemyCups;
    }

    public void setEnemyCups(int enemyCups) {
        this.enemyCups = enemyCups;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
