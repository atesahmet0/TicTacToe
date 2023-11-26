package tictactoe;

import javafx.scene.canvas.GraphicsContext;
import java.util.function.Consumer;

class GameBoardAdvanced{
  public static final int COLUMNS = 9;
  public static final int ROWS = 9;

  //Position off cell is as x, y 
  private GameCell[][] cellList = new GameCell[COLUMNS][ROWS]; 
  int width = 0, height = 0, x = 0, y = 0, thicknessOfInsideBorders = 3;

  public static void paint(GraphicsContext gc, GameBoardAdvanced gameBoard){
    double x = gameBoard.x;
    double y = gameBoard.y;
    double width = gameBoard.width;
    double height = gameBoard.height;
    double gapHorizontal = width / ROWS;
    double gapVertical = height / COLUMNS;
    int thicknessOfInsideBorders = gameBoard.thicknessOfInsideBorders;

    //Drawing horizontal lines
    for(int row = 0; row < ROWS + 1; row ++){
      gc.fillRect(x, row * gapVertical + y, width, thicknessOfInsideBorders);
    } 
    for(int column = 0; column < COLUMNS + 1; column++){
      gc.fillRect(column * gapHorizontal + x, y, thicknessOfInsideBorders, height);
    }

    //Drawing cells
    for(int column = 0; column < COLUMNS; column++){
      for(int row = 0; row < ROWS; row++){
        GameCell.paint(gc, gameBoard.cellList[column][row], 14);
      }
    }
  }

  public GameBoardAdvanced(int x, int y, int width, int height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    for(int column = 0; column < COLUMNS; column++){
      for(int row = 0; row < ROWS; row++){
        GameCell temp = new GameCell();
        temp.width = (int) getCellWidth();
        temp.height = (int) getCellHeight();
        temp.x = column * temp.width + x;
        temp.y = row * temp.height + y;
        cellList[column][row] = temp;
      }
    }
  }

  /**
   * @param x is the X coordinate of the mouse
   * @param y is the Y coordinate of the mouse
   */
  public void pointClicked(int x, int y){
    applyToAllCells( cell -> {
      if(cell.isPointInBoundaries(x, y)){
        cell.cellState = (cell.cellState == CellState.CROSS) ? CellState.CIRCLE : CellState.CROSS;
      }
    });
  }

  private void applyToAllCells(Consumer<GameCell> consumer){
    for(int column = 0; column < COLUMNS; column++){
      for(int row = 0; row < ROWS; row++){
        consumer.accept(cellList[column][row]);
      }
    }
  }

  public double getCellWidth(){
    return (double) width / COLUMNS; 
  }

  public double getCellHeight(){
    return (double) width / ROWS;
  }
}
