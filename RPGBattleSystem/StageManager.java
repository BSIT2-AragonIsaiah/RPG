/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;
import java.util.ArrayList;
/**
 *
 * @author Isaiah Aragon
 */
public class StageManager {

    private int gold = 100;
    private int currentStage = 1;
    private ArrayList<Character> playerParty;
    private ArrayList<Items> inventory = new ArrayList<>();
    public StageManager(ArrayList<Character> party) {
        this.playerParty = party;
    }

    public int getStage() {
        return currentStage;
    }

    public boolean isGameFinished() {
        return currentStage > 6;
    }

    public BattleSystem startNextBattle() {

        ArrayList<Character> enemies = EnemyFactory.generateEnemies(currentStage);

        BattleSystem battle = new BattleSystem(playerParty, enemies, inventory);

        return battle;
    }

    public void nextStage() {
        currentStage++;
    }
    
    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public boolean spendGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }
        return false;
    }
    
    public void setStage(int stage) {
        this.currentStage = stage;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    
    public ArrayList<Character> getParty() {
        return playerParty;
    }
    public ArrayList<Items> getInventory() {
        return inventory;
    }
}
