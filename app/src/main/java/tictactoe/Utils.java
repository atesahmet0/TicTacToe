package tictactoe;

/**
 * @author atesahmet0
 * Includes utils.
 */
public class Utils{

  /**
   * Checks if given point is in boundaries. Point's position is given as two seperate values.
   * Returns false if boundary is not a 2D shape
   * @param pointX x coordinate of the point.
   * @param pointY y coordinate of the point.
   * @param firstX defines first point of the boundary
   * @param firstY defines first point of the boundary
   * @param secondX defines second point of the boundary
   * @param secondY defines second point of the boundary
   */
  public static boolean isPointInBoundaries(int pointX, int pointY, int firstX, int secondX, int firstY, int secondY){
    if(firstX == secondX || firstY == secondY) return false;
    //Making sure that firstX is smaller than second so we can compare easily
    if(firstX > secondX){
      int temp = firstX;
      firstX = secondX;
      secondX = temp;
    }

    //Making sure that firstY is smaller than second so we can compare easily
    if(firstY > secondY){
      int temp = firstY;
      firstY = secondY;
      secondY = temp;
    }

    //Checking if pointX is in boundaries
    if(!(firstX < pointX && pointX < secondX)) return false;

    //Checking if pointY is in boundaries
    if(!(firstY < pointY && pointY < secondY)) return false;

    return true; 
  }
}
