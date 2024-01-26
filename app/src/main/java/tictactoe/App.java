package tictactoe;

import tictactoe.game.GameAdvanced;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;

/**
 * @author atesahmet0
 */
public class App extends Application{


  // public static final String APP_NAME = "tiktaktoe";
  public static final int WIDTH = 512; 
  public static final int HEIGHT = 512;
  public static final Color BACKGROUND_COLOR = Color.WHITE; 
  public static final Color BORDER_COLOR = Color.CRIMSON;
  public static final Color CROSS_COLOR = Color.DARKVIOLET;
  public static final Color CIRCLE_COLOR = Color.FUCHSIA;
  public static final int THICKNESS_OF_INSIDE_BORDERS = 2;
  public static final int CANVAS_MARGIN = 10;
  //Make sure that MARGIN_OF_CELL value is even! 
  public static final int MARGIN_OF_CELL = 14;

  private Canvas canvas;
  private GameAdvanced gameAdvanced;

  private int currentGameCount = 1;

  @Override
  public void start(Stage stage){
    VBox vBox = new VBox();
    Scene scene = new Scene(vBox);

    canvas = new Canvas();
    canvas.setHeight(HEIGHT);
    canvas.setWidth(WIDTH);
    vBox.getChildren().add(canvas);

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(BORDER_COLOR);
    Text text = new Text();
    vBox.getChildren().add(text);
    text.setText("Game " + currentGameCount);
    clearCanvas();
    gameAdvanced = new GameAdvanced(CANVAS_MARGIN, CANVAS_MARGIN, WIDTH - (2 * CANVAS_MARGIN), HEIGHT - (2 * CANVAS_MARGIN));
    gameAdvanced.setThicknessOfInsideBorders(THICKNESS_OF_INSIDE_BORDERS);
    gameAdvanced.setMarginOfCell(MARGIN_OF_CELL);
    gameAdvanced.setCircleColor(CIRCLE_COLOR);
    gameAdvanced.setBorderColor(BORDER_COLOR);
    gameAdvanced.setCrossColor(CROSS_COLOR);
    GameAdvanced.paint(gc, gameAdvanced);

    canvas.setOnMouseReleased(event -> {
      gameAdvanced.pointClicked( (int) event.getX(), (int) event.getY());
      paintGame(gc);
      if(gameAdvanced.isGameOver()) text.setText("Game is over!");
  
    });

    Button resetButton = new Button("Restart");
    resetButton.setOnMouseReleased( event -> {
      gameAdvanced = createGameAdvancedDefault();
      text.setText("Game " + ++currentGameCount);
      paintGame(gc);
    });

    vBox.getChildren().add(resetButton);
    vBox.setAlignment(Pos.CENTER);
    stage.setScene(scene);
    stage.show();
  }

  private void paintGame(GraphicsContext gc){
    clearCanvas();
    GameAdvanced.paint(gc, gameAdvanced);
  }

  private GameAdvanced createGameAdvancedDefault(){
    GameAdvanced gameAdvanced = new GameAdvanced(CANVAS_MARGIN, CANVAS_MARGIN, WIDTH - (2 * CANVAS_MARGIN), HEIGHT - (2 * CANVAS_MARGIN));
    gameAdvanced.setThicknessOfInsideBorders(THICKNESS_OF_INSIDE_BORDERS);
    gameAdvanced.setMarginOfCell(MARGIN_OF_CELL);
    gameAdvanced.setCircleColor(CIRCLE_COLOR);
    gameAdvanced.setBorderColor(BORDER_COLOR);
    gameAdvanced.setCrossColor(CROSS_COLOR);
    return gameAdvanced;
  }
  /**
   * Clears canvas and doesn't change initial color of it
   */
  private void clearCanvas(){
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Paint temp = gc.getFill();
    gc.setFill(BACKGROUND_COLOR);
    gc.fillRect(0, 0, WIDTH, HEIGHT);
    gc.setFill(temp);
  }

  public static void main(String[] args){
    launch(args);
  }
}
