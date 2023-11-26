package tictactoe;

import org.junit.jupiter.api.Test;

public class UtilsTest{

  @Test
  public void isPointInBoundariesTest(){
    assert(Utils.isPointInBoundaries(50, 50, 25, 75, 25, 75));
    assert(!(Utils.isPointInBoundaries(50, 50, 75, 76, 90, 95)));
  }
}
