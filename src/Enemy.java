import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Enemy {
    // Store their current hp and max hp
    private IntegerProperty hp = new SimpleIntegerProperty();
    private int maxHp;
    private String imagePath;

    public Enemy(int startingHp, String imagePath) {
        this.hp.set(startingHp);
        this.maxHp = startingHp;
        this.imagePath = imagePath;
    }

    // Deal with how enemy takes damage safely
    public void takeDamage(int damage) {
        hp.set(Math.max(0, hp.get() - damage));
    }

    // Set the enemy HP (work with it in GameModel)
    public void setHp(int hp) {
        this.hp.set(hp);
    }

    // Get the enemy HP (work with it in GameModel)
    public int getHp() {
        return hp.get();
    }

    // Makes HP bindable to UI labels/elements
    public IntegerProperty hpProperty() {
        return hp;
    }

    // Get max HP
    public int getMaxHp() {
        return maxHp;
    }

    // Check if the enemy is dead
    public boolean isDead() {
        return hp.get() <= 0;
    }

    // Get the image path
    public String getImagePath() { 
        return imagePath; 
    }


}