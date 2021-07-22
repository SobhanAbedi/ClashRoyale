package edu.ap.project.clashRoyale.client.views;

import edu.ap.project.clashRoyale.client.models.BoardModel;
import edu.ap.project.clashRoyale.client.models.CellValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class BoardView extends Group {
    public final static double CELL_WIDTH = 30.0;

    private int rowCount = 0;
    private int columnCount = 0;
    private ImageView[][] cellViews;
    private Image wallImage;
    private Image enemyKingImage;
    private Image clientKingImage;
    private Image enemyQueenImage;
    private Image clientQueenImage;
    private Image riverImage;
    private Image bridgeImage;
    private Image redRegionImage;
    private Image greenImage;
    private boolean redRegionActivated;

    /**
     * Board View Constructor
     */
    public BoardView(){
//        TODO uploadImages
        System.out.println("We Get To BoardView constructor");
        wallImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/wall.png")));
        enemyKingImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/enemyKing.png")));
        clientKingImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/clientKing.png")));
        enemyQueenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/enemyQueen.png")));
        clientQueenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/clientQueen.png")));
        riverImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/river.png")));
        bridgeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/bridge.png")));
        redRegionImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/red.png")));
        greenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Board/green.png")));
    }

    /**
     * initialize an empty grid of ImageViews
     */
    private void initializeGrid() {
        if(rowCount == 0 || columnCount == 0)
            return;
        cellViews = new ImageView[rowCount][columnCount];
        System.out.println("rowCount: " + rowCount);
        System.out.println("columnCount: " + columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView imageView = new ImageView();
                imageView.setX(column * CELL_WIDTH);
                imageView.setY(row * CELL_WIDTH);
                imageView.setFitWidth(CELL_WIDTH);
                imageView.setFitHeight(CELL_WIDTH);
                cellViews[row][column] = imageView;
                this.getChildren().add(imageView);
            }
        }
    }
    /**
     * Updates the view to reflect the state of the model
     * @param model board Model
     */
    public void update(BoardModel model) {
        //for each ImageView, set the image to correspond with the CellValue of that cell
        for (int row = 0; row < rowCount; row++){
            for (int column = 0; column < columnCount; column++){
                CellValue value = model.getCellValue(row, column);
                if (value == CellValue.Wall) {
                    cellViews[row][column].setImage(wallImage);
                }
                else if (value == CellValue.ClientKingCannon) {
                    cellViews[row][column].setImage(clientKingImage);
                }
                else if (value == CellValue.EnemyKingCannon) {
                    cellViews[row][column].setImage(enemyKingImage);
                }
                else if (value == CellValue.EnemyQueenCanon1 || value == CellValue.EnemyQueenCanon2) {
                    cellViews[row][column].setImage(enemyQueenImage);
                }
                else if (value == CellValue.ClientQueenCanon1 || value == CellValue.ClientQueenCanon2) {
                    cellViews[row][column].setImage(clientQueenImage);
                }
                else if (value == CellValue.River) {
                    cellViews[row][column].setImage(riverImage);
                }
                else if (value == CellValue.Bridge) {
                    cellViews[row][column].setImage(bridgeImage);
                }
                else if ((value == CellValue.Enemy || value == CellValue.CommonEnemy1 || value == CellValue.CommonEnemy2 ) && redRegionActivated) {
                    // in this place we can access Model and see what is the state of each Queens
                    cellViews[row][column].setImage(redRegionImage);
                }
                else {
                    cellViews[row][column].setImage(greenImage);
                }

                // Models position can be updated through this place:
            }
        }
    }

    /**
     * get rows
     * @return rows
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     *
     * @return column
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     *
     * @param rowCount set row count
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.initializeGrid();
    }

    /**
     *
     * @param columnCount set column count
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        this.initializeGrid();
    }
}