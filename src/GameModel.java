import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModel {

    // We want the current enemy, player gold, player level, player exp, etc.
    private Enemy currentEnemy;

    // Player level/exp/dmg related variables
    private IntegerProperty playerLevel = new SimpleIntegerProperty(1);
    private IntegerProperty playerExp = new SimpleIntegerProperty(0);
    private IntegerProperty playerExpToLevelUp = new SimpleIntegerProperty(10);
    // Exp rewarded per enemy defeated
    private IntegerProperty expReward = new SimpleIntegerProperty(1);
    private IntegerProperty playerDmg = new SimpleIntegerProperty(1);

    // Gold related variables
    private IntegerProperty playerGold = new SimpleIntegerProperty(0);
    // Gold rewarded per enemy defeated
    private IntegerProperty goldReward = new SimpleIntegerProperty(1);

    // Current stage
    private IntegerProperty stage = new SimpleIntegerProperty(1);

    // Constructor
    public GameModel() {
        // Start enemy with 10 HP
        currentEnemy = new Enemy(10);
    }

    // --- Core Logic Methods ---
    public void attackEnemy() {
        // Deal damage to the enemy (based on player damage stat)
        currentEnemy.takeDamage(playerDmg.get());

        if (currentEnemy.isDead()) {
            // Reward player with gold and exp
            playerExp.set(playerExp.get() + expReward.get());
            playerGold.set(playerGold.get() + (stage.get() + goldReward.get()));

            // Check if player leveled up
            checkLevelUp();

            // Go to the next stage and spawn a new enemy
            stage.set(stage.get() + 1);
            int newEnemyHp = currentEnemy.getMaxHp() + 2;
            currentEnemy = new Enemy(newEnemyHp);
        }
    }

    // Check if player leveled up
    public void checkLevelUp() {
        if(playerExp.get() >= playerExpToLevelUp.get()) {
            // Add +1 to their level
            playerLevel.set(playerLevel.get() + 1);
            // Reset their experience
            playerExp.set(playerExp.get() - playerExpToLevelUp.get());
            // Raise their exp needed
            playerExpToLevelUp.set(playerExpToLevelUp.get() + 2);
            // Raise their damage
            playerDmg.set(playerDmg.get() + 1);
        }
    }

    // Getters / Properties for binding to UI labels/elements
    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public IntegerProperty playerLevelProperty() { return playerLevel; }
    public IntegerProperty playerExpProperty() { return playerExp; }
    public IntegerProperty playerExpToLevelUpProperty() { return playerExpToLevelUp; }
    public IntegerProperty playerDmgProperty() { return playerDmg; }
    public IntegerProperty playerGoldProperty() { return playerGold; }
    public IntegerProperty goldRewardProperty() { return goldReward; }
    public IntegerProperty expRewardProperty() { return expReward; }
    public IntegerProperty stageProperty() { return stage; }

}
