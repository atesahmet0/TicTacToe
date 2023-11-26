package tictactoe.player;

import tictactoe.board.GameCell.CellState;;
/**
 * @author atesahmet0
 */
public class Player{
  private String name;
  //Players assigned state
  private CellState cellState;

  public Player(String name, CellState cellState){
    this.name = name;
    this.cellState = cellState;
  }

  public CellState getCellState(){
    return cellState;
  }
}
