package edu.AP.Project.ClashRoyale.Client.Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class BoardModel {
    private final String fileLocation = "src/edu/AP/Project/ClashRoyale/Client/Board/Board.txt";

    /**
     * constructor
     */
    public BoardModel(){
        initializeBoard(fileLocation);

//        this.rowCount = 32 + 2;
//        this.columnCount = 18 + 2;
    }
    // there is two more cells for border Wall
    private final int rowCount = 32 + 2;
    private final int columnCount = 18 + 2;
    private CellValue[][] grid;

    /**
     * get row count
     * @return row count
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     *
     * @return column count
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * initialize board
     * @param fileName board
     */
    public void initializeBoard(String fileName){

        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("row count " + rowCount +"  column count " + columnCount);
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
                        thisValue = CellValue.Bridge;
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
//        System.out.println(grid[row][column]);
        return this.grid[row][column];
    }
    public void update(){

    }

}
