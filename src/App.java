import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    // Override the start method
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the window
        primaryStage.setTitle("One Piece Battle");

        // Using the View portion of the MVC to setup the view
        GameModel gameModel = new GameModel();
        GameView gameView = new GameView();
        // GameController is like the connector between the GameModel and the GameView
        GameController gameController = new GameController(gameModel, gameView);
        Scene scene = gameView.createScene();
  
        // Set the scene, nonresizable, and show the stage
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    public static void main(String[] args) throws Exception {
        launch(args);
    }
}