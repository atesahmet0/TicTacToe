package tictactoe.board;

import tictactoe.board.GameCell.CellState;
import javafx.scene.canvas.GraphicsContext;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author atesahmet0
 */
public class GameBoardAdvanced{
  public static final int COLUMNS = 9;
  public static final int ROWS = 9;

  //Position off cell is as x, y 
  private final GameCell[][] cellList = new GameCell[COLUMNS][ROWS];
  private final int width;
  private final int height;
  private final int x;
  private final int y;
  private int thicknessOfInsideBorders = 3;
  private int marginOfCell = 0;
  private Color borderColor = Color.BLACK;
  private Color crossColor = Color.PINK;
  private Color circleColor = Color.BLUE;

  public static void paint(GraphicsContext gc, GameBoardAdvanced gameBoard){
    double x = gameBoard.x;
    double y = gameBoard.y;
    double width = gameBoard.width;
    double height = gameBoard.height;
    double gapHorizontal = width / ROWS;
    double gapVertical = height / COLUMNS;
    int thicknessOfInsideBorders = gameBoard.thicknessOfInsideBorders;

    Paint initial = gc.getFill();

    gc.setFill(gameBoard.borderColor);
    //Drawing horizontal lines
    for(int row = 0; row < ROWS + 1; row ++){
      gc.fillRect(x, row * gapVertical + y, width, thicknessOfInsideBorders);
    }

    //Drawing vertical lines
    for(int column = 0; column < COLUMNS + 1; column++){
      gc.fillRect(column * gapHorizontal + x, y, thicknessOfInsideBorders, height);
    }

    //Drawing all cells
    gameBoard.applyToAllCells( cell -> {
      GameCell mock = cell.clone();
      mock.x += thicknessOfInsideBorders;
      mock.y += thicknessOfInsideBorders;
      if(mock.cellState == CellState.CROSS) gc.setFill(gameBoard.crossColor);
      else gc.setFill(gameBoard.circleColor);
      GameCell.paint(gc, mock, gameBoard.marginOfCell);
    });

    gc.setFill(initial);
  }

  public GameBoardAdvanced(int x, int y, int width, int height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    for(int column = 0; column < COLUMNS; column++){
      for(int row = 0; row < ROWS; row++){
        GameCell temp = new GameCell();
        temp.width =  width / ROWS;
        temp.height = height / COLUMNS;
        temp.x = column * temp.width + x;
        temp.y = row * temp.height + y;
        cellList[column][row] = temp;
      }
    }
  }

  /**
  public void setCellStateByCoordinates(int x, int y, CellState state){
    applyToAllCells( cell -> {
      if(cell.isPointInBoundaries(x, y)) cell.cellState = state;
    });
  }*/

  public void setCellStateByCoordinates(int x, int y, CellState state ,Predicate<CellState> predicate){
    applyToAllCells( cell -> {
      if(predicate.test(cell.cellState) && cell.isPointInBoundaries(x, y)) cell.cellState = state;
    });
  }

  public GameCell getCell(int row, int column){
    return cellList[row][column];
  }
  private void applyToAllCells(Consumer<GameCell> consumer){
    for(int column = 0; column < COLUMNS; column++){
      for(int row = 0; row < ROWS; row++){
        consumer.accept(cellList[column][row]);
      }
    }
  }

  public void setThicknessOfInsideBorders(int thicknessOfInsideBorders) {
    this.thicknessOfInsideBorders = thicknessOfInsideBorders;
  }

  public void setMarginOfCell(int marginOfCell) {
    this.marginOfCell = marginOfCell;
  }

  public void setBorderColor(Color borderColor) {
    this.borderColor = borderColor;
  }

  public void setCrossColor(Color crossColor) {
    this.crossColor = crossColor;
  }

  public void setCircleColor(Color circleColor) {
    this.circleColor = circleColor;
  }
}
