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
    gameBoardAdvanced.setCellStateByCoordinates(x, y, currentPlayer.getCellState(), (state) -> state == CellState.EMPTY);
    currentPlayer = (currentPlayer == playerFirst) ? playerSecond : playerFirst;
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
