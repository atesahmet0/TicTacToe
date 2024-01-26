package tictactoe.game;

import tictactoe.board.GameCell.CellState;
import tictactoe.player.Player;

/**
 * Classical TicTacToeGame
 * @author atesahmet0
 */
public class Game {
    private final Player playerFirst, PlayerSecond;
    private Player currentPlayer;
    private boolean isGameOver;


    public Game (){
        playerFirst = new Player("Player1", CellState.CROSS);
        PlayerSecond = new Player("Player2", CellState.CIRCLE);
        currentPlayer = playerFirst;
    }

}
