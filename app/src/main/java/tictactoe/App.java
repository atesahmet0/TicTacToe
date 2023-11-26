package tictactoe;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * @author atesahmet0
 */
public class App extends Application{


  public static final String APP_NAME = "TicTacToe";
  public static final int WIDTH = 512;
  public static final int HEIGHT = 512;
  public static final Color BACKGROUND_COLOR = Color.WHITESMOKE; 
  public static final int THICKNESS_OF_INSIDE_BORDERS = 2;
  public static final int CANVAS_MARGIN = 10;

  private Canvas canvas;

  @Override
  public void start(Stage stage) throws Exception{
    VBox vBox = new VBox();
    Scene scene = new Scene(vBox);

    canvas = new Canvas();
    canvas.setHeight(HEIGHT);
    canvas.setWidth(WIDTH);
    vBox.getChildren().add(canvas);

    Text text = new Text();
    vBox.getChildren().add(text);

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.DARKMAGENTA);
    text.setText(String.valueOf(WIDTH - CANVAS_MARGIN));
    GameBoardAdvanced gba = new GameBoardAdvanced(CANVAS_MARGIN, CANVAS_MARGIN, (WIDTH - (2 * CANVAS_MARGIN)), HEIGHT - (2 * CANVAS_MARGIN));
    gba.thicknessOfInsideBorders = THICKNESS_OF_INSIDE_BORDERS;
    GameBoardAdvanced.paint(gc, gba);
    
    canvas.setOnMouseReleased(event -> {
      text.setText("x is: " + event.getX() + " y is: " + event.getY());
      gba.pointClicked((int) event.getX(),(int) event.getY());
      clearCanvas();
      GameBoardAdvanced.paint(gc, gba);
    });

    stage.setScene(scene);
    stage.show();
  }

  /**
   * Clears canvas and doesnt changes initial color of it
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
