package tictactoe.board;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class GameBoard{


    protected int rowCount, columnCount;

    protected GameCell[][] cellList;
    protected int width = 0, height = 0, x = 0, y = 0, thicknessOfInsideBorders = 3, marginOfCell = 0;

    protected Color borderColor = Color.BLACK, crossColor = Color.PINK, circleColor = Color.BLUE;

    public GameBoard(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        for(int column = 0; column < columnCount; column++){
            for(int row = 0; row < rowCount; row++){
                GameCell temp = new GameCell();
                temp.width =  width / rowCount;
                temp.height = height / columnCount;
                temp.x = column * temp.width + x;
                temp.y = row * temp.height + y;
                cellList[column][row] = temp;
            }
        }
    }

    public static void paint(GraphicsContext gc, GameBoard gameBoard){
        double x = gameBoard.x;
        double y = gameBoard.y;
        double width = gameBoard.width;
        double height = gameBoard.height;
        double gapHorizontal = width / gameBoard.getRowCount();
        double gapVertical = height / gameBoard.getColumnCount();
        int thicknessOfInsideBorders = gameBoard.thicknessOfInsideBorders;

        Paint initial = gc.getFill();

        gc.setFill(gameBoard.borderColor);
        //Drawing horizontal lines
        for(int row = 0; row < gameBoard.getRowCount() + 1; row ++){
            gc.fillRect(x, row * gapVertical + y, width, thicknessOfInsideBorders);
        }

        //Drawing vertical lines
        for(int column = 0; column < gameBoard.getColumnCount() + 1; column++){
            gc.fillRect(column * gapHorizontal + x, y, thicknessOfInsideBorders, height);
        }

        //Drawing all cells
        gameBoard.applyToAllCells( cell -> {
            GameCell mock = cell.clone();
            mock.x += thicknessOfInsideBorders;
            mock.y += thicknessOfInsideBorders;
            if(mock.cellState == GameCell.CellState.CROSS) gc.setFill(gameBoard.crossColor);
            else gc.setFill(gameBoard.circleColor);
            GameCell.paint(gc, mock, gameBoard.marginOfCell);
        });

        gc.setFill(initial);
    }

    public void paint(GraphicsContext gc){
        GameBoard.paint(gc, this);
    }

    private void applyToAllCells(Consumer<GameCell> consumer){
        for(int column = 0; column < getColumnCount(); column++){
            for(int row = 0; row < getRowCount(); row++){
                consumer.accept(cellList[column][row]);
            }
        }
    }
    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }


}
