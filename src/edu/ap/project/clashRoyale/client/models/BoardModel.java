package edu.ap.project.clashRoyale.client.models;

import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PointDouble;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;
import edu.ap.project.clashRoyale.model.Card;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardModel {
    private final String fileLocation = "src/edu/ap/project/clashRoyale/client/board/Board.txt";
    private int rowCount;
    private int columnCount;
    private CellValue[][] grid;
    private int gameSteps;
    private int currentStep;
    private DoubleProperty elixirRemaining;
    private DoubleProperty elixirBarValue;
    private ArrayList<ArrayList<ForceState>> stateList;
    public GameTimer gameTimer;
    private float elixirSpeed;
    private float deltaTime;
    private final int playerSide;
    private Card[] deckCards;
    private String OpponentName;
    private boolean EPTLeftIsAlive;
    private boolean EPTRightIsAlive;

    /**
     * constructor
     */
    public BoardModel(int playerSide, Card[] deckCards, String OpponentName){
        this.playerSide = playerSide;
        this.deckCards = deckCards;
        this.OpponentName = OpponentName;
        EPTLeftIsAlive = true;
        EPTRightIsAlive = true;
        System.out.println("Hi. you got to board model constructor");
        gameSteps = (int) (GlobalVariables.GAME_DURATION / GlobalVariables.DELTA_TIME);
        stateList = new ArrayList<>(gameSteps);
        currentStep = 0;
        elixirRemaining = new SimpleDoubleProperty(0.0);
        elixirRemaining.setValue(4.0);
        elixirBarValue = new SimpleDoubleProperty(0.0);
        elixirBarValue.setValue(0.4);
        initializeBoard(fileLocation);
        deltaTime = GlobalVariables.DELTA_TIME;
        gameTimer = new GameTimer(GlobalVariables.GAME_DURATION, deltaTime);
        elixirSpeed = 0.5f;
    }


    /**
     * initialize board
     * @param fileName board
     */
    public void initializeBoard(String fileName){
        // there is two more cells for border Wall
        rowCount = GlobalVariables.QUARTER_LENGTH * 2 + 2;
        columnCount = GlobalVariables.QUARTER_WIDTH * 2 + 2;

        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load file");
            e.printStackTrace();
        }
        grid = new CellValue[rowCount][columnCount];
        int row = 0;
        int enemyKingRow = 0;
        int enemyKingColumn = 0;
        int clientKingRow = 0;
        int clientKingColumn = 0;
        int enemyQueen1Row = 0;
        int enemyQueen1Column = 0;
        int enemyQueen2Row = 0;
        int enemyQueen2Column = 0;
        int clientQueen1Row = 0;
        int clientQueen1Column = 0;
        int clientQueen2Row = 0;
        int clientQueen2Column = 0;
        while (scanner.hasNextLine()){
            int column = 0;
            String line= scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()){
                String value = lineScanner.next();
                CellValue thisValue;
                switch (value) {
                    case "R":
                        thisValue = CellValue.River;
                        break;
                    case "W":
                        thisValue = CellValue.Wall;
                        break;
                    case "B":
                        thisValue = CellValue.Bridge1;
                        break;
                    case "N":
                        thisValue = CellValue.Bridge2;
                        break;
                    case "E":
                        thisValue = CellValue.Enemy;
                        break;
                    case "C":
                        thisValue = CellValue.Client;
                        break;
                    case "K":
                        thisValue = CellValue.ClientKingCannon;
                        clientKingColumn = column;
                        clientKingRow = row;
                        break;
                    case "Q":
                        thisValue = CellValue.ClientQueenCanon1;
                        clientQueen1Column = column;
                        clientQueen1Row = row;
                        break;
                    case "G":
                        thisValue = CellValue.ClientQueenCanon2;
                        clientQueen2Column = column;
                        clientQueen2Row = row;
                        break;
                    case "T":
                        thisValue = CellValue.EnemyKingCannon;
                        enemyKingColumn = column;
                        enemyKingRow = row;
                        break;
                    case "Y":
                        thisValue = CellValue.EnemyQueenCanon1;
                        enemyQueen1Column = column;
                        enemyQueen1Row = row;
                        break;
                    case "U":
                        thisValue = CellValue.EnemyQueenCanon2;
                        enemyQueen2Column = column;
                        enemyQueen2Row = row;
                        break;
                    case "I":
                        thisValue = CellValue.ClientKingWall;
                        break;
                    case "O":
                        thisValue = CellValue.ClientQueenWall1;
                        break;
                    case "P":
                        thisValue = CellValue.ClientQueenWall2;
                        break;
                    case "A":
                        thisValue = CellValue.EnemyKingWall;
                        break;
                    case "S":
                        thisValue = CellValue.EnemyQueenWall1;
                        break;
                    case "D":
                        thisValue = CellValue.EnemyQueenWall2;
                        break;
                    case "F":
                        thisValue = CellValue.CommonClient1;
                        break;
                    case "H":
                        thisValue = CellValue.CommonClient2;
                        break;
                    case "J":
                        thisValue = CellValue.CommonEnemy1;
                        break;
                    case "L":
                        thisValue = CellValue.CommonEnemy2;
                        break;
                    default:
                        thisValue = CellValue.Enemy;
                }
                grid[row][column] = thisValue;
                column++;
            }
            row++;
        }

    }

    /**
     * get cell value
     * @param row of board
     * @param column of board
     * @return Cell value
     */
    public CellValue getCellValue(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }

    public void nextStep() {
        currentStep++;
        gameTimer.step();
        if(elixirRemaining.get() < 10) {
            elixirRemaining.setValue(elixirRemaining.getValue() + elixirSpeed * deltaTime);
            elixirBarValue.setValue(elixirBarValue.getValue() + elixirSpeed * deltaTime / 10);
        }
        if(elixirRemaining.get() > 10){
            elixirRemaining.setValue(10);
            elixirBarValue.setValue(1);
        }
        if(elixirSpeed == 0.5f && currentStep >= ((int) 120/deltaTime))
            elixirSpeed = 1;
    }

    public ArrayList<ForceState> getCurrentState() {
        return stateList.get(currentStep);
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public float getCurrentTime() {
        return deltaTime * currentStep;
    }

    public DoubleProperty getElixirRemaining() {
        return elixirRemaining;
    }

    public DoubleProperty getElixirBarValue() {
        return elixirBarValue;
    }

    public void useElixir(double used) {
        elixirRemaining.setValue(elixirRemaining.getValue() - used);
        elixirBarValue.setValue(elixirBarValue.getValue() - used / 10);
    }

    public void updateStateList(ArrayList<ArrayList<ForceState>> newStateList, int rebaseStep) {
        int listSize = stateList.size();
        if (listSize > rebaseStep) {
            stateList.subList(rebaseStep, listSize).clear();
        }
        if(listSize < rebaseStep)
            System.err.println("Missing Steps");
        stateList.addAll(newStateList.subList(rebaseStep, newStateList.size()));
    }

    public int getPlayerSide() {
        return playerSide;
    }

    public class GameTimer {
        private float timeRemaining;
        private float stepTime;
        public GameTimer(float gameDuration, float deltaTime) {
            timeRemaining = gameDuration;
            stepTime = deltaTime;
        }

        private void step() {
            timeRemaining -= stepTime;
        }

        public float getTimeRemaining() {
            return timeRemaining;
        }

        public String getTimeRemaingString() {
            StringBuilder stringBuilder = new StringBuilder();
            int minutes = (int)timeRemaining/60;
            int seconds = (int) timeRemaining - 60 * minutes;
            stringBuilder.append(minutes);
            stringBuilder.append(":");
            if(seconds < 10)
                stringBuilder.append(0);
            stringBuilder.append(seconds);
            return stringBuilder.toString();
        }
    }

    public Card getDeckCards(int pos) {
        if(pos > 4)
            return null;
        return deckCards[pos - 1];
    }

    public String getOpponentName() {
        return OpponentName;
    }

    public Card removeDeckCard(int pos) {
        if(pos > 4)
            return null;
        Card desiredCard = deckCards[pos - 1];
        deckCards[pos - 1] = deckCards[4];
        for(int i = 4; i < GlobalVariables.DECK_SIZE - 1; i++) {
            deckCards[i] = deckCards[i + 1];
        }
        deckCards[GlobalVariables.DECK_SIZE - 1] = desiredCard;
        return desiredCard;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void EPTLeftIsDestroyed() {
        EPTRightIsAlive = false;
    }

    public void EPTRightIsDestroyed() {
        EPTRightIsAlive = false;
    }

    public boolean isEPTLeftIsAlive() {
        return EPTLeftIsAlive;
    }

    public boolean isEPTRightIsAlive() {
        return EPTRightIsAlive;
    }

    public boolean canPlace(int row, int column, boolean isSpell) {
        if(isSpell && getCellValue(row, column) != CellValue.Wall && getCellValue(row, column) != CellValue.River)
            return true;
        switch (getCellValue(row, column)) {
            case Wall:
            case Enemy:
            case River:
                return false;
            case Bridge1:
            case CommonEnemy1:
                if(EPTLeftIsAlive)
                    return false;
                break;
            case Bridge2:
            case CommonEnemy2:
                if(EPTRightIsAlive)
                    return false;
                break;
        }
        return !hasCollision(row, column);
    }

    public boolean hasCollision(int row, int column) {
        for(ForceState forceState : stateList.get(stateList.size() - 1)) {
            PointDouble forceLocation = new PointDouble(forceState.getLocation());
            if(playerSide == 1) {
                forceLocation.setLocation(((GlobalVariables.QUARTER_WIDTH + 1) - forceLocation.x), ((GlobalVariables.QUARTER_LENGTH + 1) + forceLocation.y));
            } else {
                forceLocation.setLocation(((GlobalVariables.QUARTER_WIDTH + 1) + forceLocation.x), ((GlobalVariables.QUARTER_LENGTH + 1) - forceLocation.y));
            }
            double distance = forceLocation.distance(new PointDouble(column, row));
            float forceRadius = getRadius(forceState.getForceName());
            if(forceRadius < 2.5f && forceRadius + 0.5 > distance)
                return true;
        }
        return false;
    }

    public static float getRadius(String forceName) {
        switch (forceName) {
            case "Archer":
            case "Barbarian":
            case "Inferno Tower":
            case "Mini P.E.K.K.A.":
            case "Valkyrie":
            case "Wizard":
                return 0.5f;
            case "Baby Dragon":
            case "Cannon":
            case "Giant":
                return 0.75f;
            case "Princess Tower":
                return 1.5f;
            case "King Tower":
                return 2f;
            case "Fireball":
                return 2.5f;
            case "Arrows":
                return 3f;
            case "Rage":
                return 5f;
            default:
                return 0.2f;
        }
    }
}
