package tictactoe.game;

import org.junit.jupiter.api.Test;

class GameAdvancedTest{
	
	@Test
	public static void checkWinningConditionTest(){
    final int width = 512, height = 512;
    GameAdvanced mockGame = new GameAdvanced(0, 0, width, height);
    mockGame.pointClicked(1, 1);
    mockGame.pointClicked(58, 1);
    mockGame.pointClicked(120, 1);
    assert(GameAdvanced.checkWinningCondition(mockGame));
	
	}
}
