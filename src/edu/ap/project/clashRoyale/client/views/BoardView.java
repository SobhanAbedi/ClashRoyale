package edu.ap.project.clashRoyale.client.views;

import edu.ap.project.clashRoyale.client.models.BoardModel;
import edu.ap.project.clashRoyale.client.models.CellValue;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PointDouble;
import edu.ap.project.clashRoyale.server.model.gameEngine.ActionKind;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Objects;

public class BoardView extends Group {
    public final static double CELL_WIDTH = 24;

    private int rowCount = 0;
    private int columnCount = 0;
    private ImageView[][] cellViews;
    private  int playerSide;
    private HashMap<Integer, ImageView> forces;
    private HashMap<Integer, ActionKind> previousActionKind;
    public final Image wallImage;
    public final Image enemyKingImage;
    public final Image clientKingImage;
    public final Image enemyQueenImage;
    public final Image clientQueenImage;
    public final Image riverImage;
    public final Image bridgeImage;
    public final Image redRegionImage;
    public final Image whiteRegionImage;
    public final Image greenImage;
    private boolean redRegionActivated;

    /**
     * Board View Constructor
     */
    public BoardView(){
//        TODO uploadImages
        System.out.println("We Get To BoardView constructor");
        wallImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/wall.png")));
        enemyKingImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/enemyKing.png")));
        clientKingImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/clientKing.png")));
        enemyQueenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/enemyQueen.png")));
        clientQueenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/clientQueen.png")));
        riverImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/river.png")));
        bridgeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/bridge.png")));
        redRegionImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/red.png")));
        whiteRegionImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/white.png")));
        greenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../images/Board/green.png")));
        forces = new HashMap<>(50);
        previousActionKind = new HashMap<>(50);
        playerSide = 0;
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
    public void DrawBaseBoard(BoardModel model) {
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
                else if (value == CellValue.Bridge1 || value == CellValue.Bridge2) {
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

    public int getPlayerSide() {
        return playerSide;
    }

    public void setPlayerSide(int playerSide) {
        this.playerSide = playerSide;
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

    public void setCellView(int row, int column, Image image) {
        cellViews[row][column].setImage(image);
    }

    public Image getCellView(int row, int column) {
        return cellViews[row][column].getImage();
    }

    public static PointDouble importLocation(PointDouble rawLocation, int playerSide) {
        if(playerSide == 1)
            return new PointDouble(CELL_WIDTH * ((GlobalVariables.QUARTER_WIDTH + 1) - rawLocation.x), CELL_WIDTH * ((GlobalVariables.QUARTER_LENGTH + 1) + rawLocation.y));
        else
            return new PointDouble(CELL_WIDTH * ((GlobalVariables.QUARTER_WIDTH + 1) + rawLocation.x), CELL_WIDTH * ((GlobalVariables.QUARTER_LENGTH + 1) - rawLocation.y));
    }

    public float translateAngle(float angel) {
        if(playerSide == 1)
            return angel + 180;
        return angel;
    }

    public void updateForce(ForceState forceNewState) {
        ImageView imageView = forces.get(forceNewState.getForceID());
        PointDouble location = BoardView.importLocation(forceNewState.getLocation(), playerSide);
        imageView.setX(location.x);
        imageView.setY(location.y);
        imageView.setRotate(translateAngle(forceNewState.getAngle()));
        if(previousActionKind.get(forceNewState.getForceID()) != forceNewState.getActionKind()) {
            //TODO: changeImage
        }
    }

    public void addForce(ForceState force) {
        //TODO: finish Function
    }

    public void removeForce(int forceID) {
        //TODO: finish Function
    }

    public void makeSureIsDead(int forceId) {
        //TODO: finish Function
    }
}
