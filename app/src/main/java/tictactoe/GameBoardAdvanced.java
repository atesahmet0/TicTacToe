package tictactoe;

import javafx.scene.canvas.GraphicsContext;

class GameBoardAdvanced{
  public static final int COLUMNS = 9;
  public static final int ROWS = 9;

  //Position off cell is as x, y 
  private GameCell[][] cellList = new GameCell[9][9]; 
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
  }

  public GameBoardAdvanced(int x, int y, int width, int height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
}
