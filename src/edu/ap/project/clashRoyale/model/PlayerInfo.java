package edu.ap.project.clashRoyale.model;

public class PlayerInfo {
    private String username;
    private int userID;
    private int score;
    private int coins;
    private int level;

    public PlayerInfo(String username, int userID, int score, int coins, int level) {
        this.username = username;
        this.userID = userID;
        this.score = score;
        this.coins = coins;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return userID;
    }

    public int getScore() {
        return score;
    }

    public int getCoins() {
        return coins;
    }

    public int getLevel() {
        return level;
    }
}
