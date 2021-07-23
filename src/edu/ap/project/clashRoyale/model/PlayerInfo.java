package edu.ap.project.clashRoyale.model;

public class PlayerInfo {
    private String username;
    private int userID;
    private int score;
    private int coins;
    private int level;

    /**
     * Constructor
     * @param username username
     * @param userID user ID
     * @param score Score
     * @param coins coins
     * @param level client level
     */
    public PlayerInfo(String username, int userID, int score, int coins, int level) {
        this.username = username;
        this.userID = userID;
        this.score = score;
        this.coins = coins;
        this.level = level;
    }

    /**
     * get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * get user ID
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * get Score
     * @return player score
     */
    public int getScore() {
        return score;
    }

    /**
     * get coins
     * @return player coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * get player level
     * @return
     */
    public int getLevel() {
        return level;
    }
}
