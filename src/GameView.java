import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameView {
    // This is how we will deal with the view of the game
    // View portion of MVC

    // Variables for the view components
    private BorderPane rootPane;
    private StackPane centerStackPane;
    private Label hpLabel;
    private Button attackButton;
    private ImageView enemyImageView;
    private Label stageLabel;
    private Label goldLabel;
    private Label levelLabel;
    private Label expLabel;

    // Actual layout of the game scene
    public GameView() {
        // Root layout for the entire screen
        rootPane = new BorderPane();
        rootPane.setPrefSize(900, 600);
        rootPane.setStyle("-fx-background-color: LIGHTBLUE;");

        // Padding (top, right, bottom, left)
        // This is to get the goldLabel, levelLabel, and expLabel closer to the center pane
        rootPane.setPadding(new Insets(100, 0, 100, 0));

        // Center area where the enemy and UI elements will go
        centerStackPane = new StackPane();
        centerStackPane.setMaxSize(450, 300);
        centerStackPane.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; -fx-border-width: 2;");
        rootPane.setCenter(centerStackPane);

        // Enemy image setup on the center stack pane
        enemyImageView = new ImageView("file:src/images/enemy.png");
        enemyImageView.setFitWidth(215);
        enemyImageView.setFitHeight(175);
        centerStackPane.getChildren().add(enemyImageView);

        // HP Label setup
        hpLabel = new Label("HP: 100");
        hpLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        // Attack Button setup
        attackButton = new Button("Attack");
        attackButton.setStyle("-fx-font-size: 16; -fx-background-radius: 13; -fx-border-radius: 13;");

        // Layout for enemy image, hp label, and attack button stacked VERTICALLY
        VBox vbox = new VBox(10, enemyImageView, hpLabel, attackButton);
        vbox.setAlignment(Pos.CENTER);
        centerStackPane.getChildren().add(vbox);

        // Stage Label at the top center of the screen
        stageLabel = new Label("Stage 1");
        stageLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        // Add the Gold Label at the top of the screen
        goldLabel = new Label("Gold: 0");
        goldLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        // Use VBox to stack stage and gold labels vertically
        VBox topVBox = new VBox(5, stageLabel, goldLabel);
        topVBox.setAlignment(Pos.CENTER);
        rootPane.setTop(topVBox);

        // Add the level and experience labels at the bottom of the screen
        levelLabel = new Label("Level: 1");
        levelLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        expLabel = new Label("EXP: 0/10");
        expLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Use VBox to stack level and exp labels vertically
        VBox bottomVBox = new VBox(5, levelLabel, expLabel);
        bottomVBox.setAlignment(Pos.CENTER);
        rootPane.setBottom(bottomVBox);
    }

    // --- Getters (for the Controller to use later) ---
    public BorderPane getRootPane() {
        return rootPane;
    }

    public Label getStageLabel() {
        return stageLabel;
    }

    public Label getGoldLabel() {
        return goldLabel;
    }

    public Label getHpLabel() {
        return hpLabel;
    }

    public Button getAttackButton() {
        return attackButton;
    }

    public Label getLevelLabel() {
        return levelLabel;
    }

    public Label getExpLabel() {
        return expLabel;
    }

    public ImageView getEnemyImageView() {
        return enemyImageView;
    }

    // Build the Scene directly from the view
    public Scene createScene() {
        return new Scene(rootPane, 900, 600);
    }
}