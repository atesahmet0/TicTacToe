package tictactoe;

import javafx.scene.canvas.GraphicsContext;

class GameCell{
  //Default stat is CROSS
  private CellState cellState = CellState.EMPTY;
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

  private static void paintCircle(GraphicsContext gc, GameCell gameCell){
    double x = gameCell.x;
    double y = gameCell.y;
    double width = gameCell.width;
    double height = gameCell.height;
    double inline = gameCell.inline;

    gc.strokeOval(x, y, width - inline, height - inline);
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
}

enum CellState{
  CROSS,
  CIRCLE,
  EMPTY
}
