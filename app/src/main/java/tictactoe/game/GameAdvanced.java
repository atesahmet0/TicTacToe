package tictactoe.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tictactoe.board.GameBoardAdvanced;
import tictactoe.board.GameCell.CellState;
import tictactoe.player.Player;
/**
 * This is a class that holds everything about a single game.
 * GameAdvanced object is responsible from board, players, rules etc...
 * Also, this object paints current state of the game through paint method.
 * @author atesahmet0
 */
public class GameAdvanced{
  private final GameBoardAdvanced gameBoardAdvanced;
  private final Player playerFirst, playerSecond;
  private Player currentPlayer;
  private boolean isGameOver = false;

  /**
   * Returns true if the winningCondition satisfied
   * winning Condition is having minimum amounts of States in a row or column
   */
  public static boolean checkWinningCondition(GameAdvanced gameAdvanced){
    final int minAmountInARow = 4;
    GameBoardAdvanced gba = gameAdvanced.gameBoardAdvanced;
    for(int row = 0; row < GameBoardAdvanced.ROWS; row++){
      for(int column = 0; column < GameBoardAdvanced.COLUMNS; column++){
        CellState currentCell = gba.getCell(row,column).getCellState();
        if(currentCell == CellState.EMPTY) continue; 
        //Check row match
        if(!(row + minAmountInARow > GameBoardAdvanced.ROWS)){
          boolean rowMatch = true;
          //Checks for the cells next to initial
          for(int i = 1; i < minAmountInARow; i++){
            if(currentCell != gba.getCell(row + i, column).getCellState()) rowMatch = false;
          }
          if(rowMatch) return true;
        }  

        //Check column match
        if(!(column + minAmountInARow > GameBoardAdvanced.COLUMNS)){
          //Checks for the cells next to initial. 
          boolean columnMatch = true;
          for(int i = 1; i < minAmountInARow; i++){
            if(currentCell != gba.getCell(row, column + i).getCellState()) columnMatch = false;
          }
          if(columnMatch) return true;
        }
      }
    }
    return false;
  }

  public static void paint(GraphicsContext gc, GameAdvanced gameAdvanced){
    GameBoardAdvanced.paint(gc, gameAdvanced.gameBoardAdvanced);
  }

  public GameAdvanced(int x, int y, int width, int height){
    gameBoardAdvanced = new GameBoardAdvanced(x, y, width, height);
    playerFirst = new Player("Player1", CellState.CROSS);
    playerSecond = new Player("Player2", CellState.CIRCLE);
    currentPlayer = playerFirst;
  }

  public void pointClicked(int x, int y){
    if(isGameOver) return;
    gameBoardAdvanced.setCellStateByCoordinates(x, y, currentPlayer.getCellState(), (state) -> state == CellState.EMPTY);
    currentPlayer = (currentPlayer == playerFirst) ? playerSecond : playerFirst;
    if(checkWinningCondition(this)) isGameOver = true;
  }

  public boolean isGameOver(){
    return isGameOver;
  }

  public void setThicknessOfInsideBorders(int thicknessOfInsideBorders) {
    gameBoardAdvanced.setThicknessOfInsideBorders(thicknessOfInsideBorders);
  }

  public void setMarginOfCell(int marginOfCell) {
    gameBoardAdvanced.setMarginOfCell(marginOfCell);
  }

  public void setBorderColor(Color borderColor) {
    gameBoardAdvanced.setBorderColor(borderColor);
  }

  public void setCrossColor(Color crossColor) {
    gameBoardAdvanced.setCrossColor(crossColor);
  }

  public void setCircleColor(Color circleColor) {
    gameBoardAdvanced.setCircleColor(circleColor);
  }
}
