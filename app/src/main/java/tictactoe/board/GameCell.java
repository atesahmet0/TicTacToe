package tictactoe.board;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.canvas.GraphicsContext;
import tictactoe.Utils;
/**
 * @author atesahmet0
 * Represents a single piece of cell in the board.
 */
public class GameCell{
  //Default state is empty 
  CellState cellState = CellState.EMPTY;
  //Coordinates as x, y and refer to top left pane
  int x = 0, y = 0;
  int height = 0, width = 0;
  // Inline amount as pixels
  int inline = 4;

  /**
   * This function paints given gameCell by using provided GraphicsContext
   * 
   * @param gc GraphicsContext to be painted
   * @param gameCell GameCell to paint
   */
  public static void paint(GraphicsContext gc, GameCell gameCell){
    switch(gameCell.cellState){
      case EMPTY:
      break;
      case CROSS:
      paintCross(gc, gameCell);
      break;
      case CIRCLE:
      paintCircle(gc, gameCell);
      break;
    }
  }

  /**
   * Paints object on the GraphicsContext.
   * @param gc GraphicsContext to be painted on
   * @param gameCell GameCell to be painted
   * @param margin margin from left and right make sure this is an even value or else results may be unpredictable!
   */
  public static void paint(GraphicsContext gc, GameCell gameCell, int margin){
    GameCell mock = gameCell.clone();
    mock.width -= margin;
    mock.x += margin / 2;
    mock.height -= margin;
    mock.y += margin / 2;
    switch(mock.cellState){
      case EMPTY:
      break;
      case CROSS:
      paintCross(gc, mock);
      break;
      case CIRCLE:
      paintCircle(gc, mock);
      break;
    }
 
  }

  private static void paintCircle(GraphicsContext gc, GameCell gameCell){
    double x = gameCell.x;
    double y = gameCell.y;
    double width = gameCell.width;
    double height = gameCell.height;
    double inline = gameCell.inline;

    gc.fillOval(x, y, width - inline, height - inline);
    Paint initial = gc.getFill();
    gc.setFill(Color.WHITE);
    gc.fillOval(x + inline, y + inline, width -(3 * inline), height - (3 * inline));
    gc.setFill(initial);
  }

  private static void paintCross(GraphicsContext gc, GameCell gameCell){
    double x = gameCell.x;
    double y = gameCell.y;
    double width = gameCell.width;
    double height = gameCell.height;
    double inline = gameCell.inline;

    double[] xPoints = new double[]{x + inline, x, x + width - inline, x + width};
    double[] yPoints = new double[]{y, y + inline, y + height, y + height - inline};
    gc.fillPolygon(xPoints, yPoints, 4);

    xPoints = new double[]{x, x + inline, x + width, x + width - inline};
    yPoints = new double[]{y + height - inline, y + height, y + inline, y};
    gc.fillPolygon(xPoints, yPoints, 4);
  }

  public boolean isPointInBoundaries(int x, int y){
    return Utils.isPointInBoundaries(x, y, this.x, this.x + width, this.y, this.y + height);
  }

  public GameCell(){
    x = 0;
    y = 0;
    height = 0;
    width = 0;
    cellState = CellState.EMPTY;
  }

  public GameCell(int x, int y, int width, int height, CellState cellState){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.cellState = cellState;
  }

  public GameCell clone(){
    return new GameCell(
      this.x,
      this.y,
      this.width,
      this.height,
      this.cellState
    );
  }
 
  public enum CellState{
    CROSS,
    CIRCLE,
    EMPTY
  }
}


