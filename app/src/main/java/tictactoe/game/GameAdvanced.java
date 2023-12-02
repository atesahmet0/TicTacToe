package tictactoe.game;

import javafx.scene.canvas.GraphicsContext;
import tictactoe.board.GameBoardAdvanced;
import tictactoe.board.GameCell.CellState;
import tictactoe.player.Player;
import javafx.scene.paint.Color;
/**
 * This is a class that holds everything about an single game.
 * GameAdvanced object is responsible from board, players, rules etc...
 * Also this object paints current state of the game through paint method.
 * @author atesahmet0
 */
public class GameAdvanced{
  private GameBoardAdvanced gameBoardAdvanced;
  private Player playerFirst, playerSecond;
  private Player currentPlayer;
  private boolean isGameOver = false;

  /**
   * Returns true if the winningCondition satisfied
   * winningConfition is having minimum amounts of States in a row or column
   */
  public static boolean checkWinningCondition(GameAdvanced gameAdvanced){
    final int minAmountInARow = 4;
    GameBoardAdvanced gba = gameAdvanced.gameBoardAdvanced;
    CellState currentCell = CellState.EMPTY; 
    for(int row = 0; row < gba.getRowCount(); row++){
      for(int column = 0; column < gba.getColumnCount(); column++){
        currentCell = gba.getCell(row,column).getCellState();
        if(currentCell == CellState.EMPTY) continue; 
        //Check row match
        if(!(row + minAmountInARow > gba.getRowCount())){
          boolean rowMatch = true;
          //Checks for the cells next to initial
          for(int i = 1; i < minAmountInARow; i++){
            if(currentCell != gba.getCell(row + i, column).getCellState()) rowMatch = false;
          }
          if(rowMatch) return true;
        }  

        //Check column match
        if(!(column + minAmountInARow > gba.getColumnCount())){
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

  public void setCrossColor(Color color){
    gameBoardAdvanced.setCrossColor(color);
  }

  public void setBorderColor(Color color){
    gameBoardAdvanced.setBorderColor(color);
  }

  public void setCircleColor(Color color){
    gameBoardAdvanced.setCircleColor(color);
  }

  public void setMarginOfCell(int margin){
    gameBoardAdvanced.setMarginOfCell(margin);
  }

  public void setThicknessOfInsideBorders(int thickness){
    gameBoardAdvanced.setThicknessOfInsideBorders(thickness);
  }

  public Player getCurrentPlayer(){
    return currentPlayer;
  }

  public boolean isGameOver(){
    return isGameOver;
  }
  /**
   * DONT CHANGE BOARDS WIDTH AND HEIGHT 
   */
  @Deprecated
  public void setWidth(int width){
    gameBoardAdvanced.setWidth(width);
  }

  @Deprecated
  public void setHeight(int height){
    gameBoardAdvanced.setHeight(height);
  }
}
