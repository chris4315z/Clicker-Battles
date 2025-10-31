import javafx.beans.binding.Bindings;

public class GameController {
    // Need the GameModel and GameView so they can communicate
    GameModel gameModel = new GameModel();
    GameView gameView = new GameView();

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;

        // --- Bind labels to model properties ---
        gameView.getStageLabel().textProperty().bind(gameModel.stageProperty().asString("Stage: %d"));
        gameView.getHpLabel().textProperty().bind(gameModel.getCurrentEnemy().hpProperty().asString("HP: %d"));
        gameView.getGoldLabel().textProperty().bind(gameModel.playerGoldProperty().asString("Gold: %d"));
        gameView.getLevelLabel().textProperty().bind(gameModel.playerLevelProperty().asString("Level: %d"));
        
        // Need to bind the label this way because of the "EXP: %d / %d", can't "just do it"
        gameView.getExpLabel().textProperty().bind(
            Bindings.createStringBinding(
            () -> String.format("EXP: %d / %d",
                gameModel.playerExpProperty().get(),
                gameModel.playerExpToLevelUpProperty().get()),
                gameModel.playerExpProperty(),
                gameModel.playerExpToLevelUpProperty()
            )
        );

        // --- Handle button click ---
        gameView.getAttackButton().setOnAction(e -> {
            gameModel.attackEnemy();

            // Rebind HP when new enemy spawns
            gameView.getHpLabel().textProperty().unbind();
            gameView.getHpLabel().textProperty().bind(gameModel.getCurrentEnemy().hpProperty().asString("HP: %d"));
        });
    }
}