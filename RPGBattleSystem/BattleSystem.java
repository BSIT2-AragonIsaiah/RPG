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
public class BattleSystem {

    private ArrayList<Character> party;
    private ArrayList<Character> enemies;
    

    private int turnIndex = 0;
    private boolean playerTurn = true;

    private String lastAction = "";

    private Runnable uiCallback; 

    private ArrayList<Items> inventory;

    public BattleSystem(ArrayList<Character> party, ArrayList<Character> enemies, ArrayList<Items> inventory) {
        this.party = party;
        this.enemies = enemies;
        this.inventory = inventory;
    }

    // UI HOOK 
    public void setUICallback(Runnable callback) {
        this.uiCallback = callback;
    }

    private void refreshUI() {
        if (uiCallback != null) {
            uiCallback.run();
        }
    }

    // GETTERS 
    public ArrayList<Character> getParty() { return party; }
    public ArrayList<Character> getEnemies() { return enemies; }
    public String getLastAction() { return lastAction; }
    public int getTurnIndex() { return turnIndex; }
    public boolean isPlayerTurn() { return playerTurn; }

    // PLAYER ACTIONS 

    public void playerAttack(int targetIndex) {
        Character attacker = party.get(turnIndex);
        Character target = enemies.get(targetIndex);

        int dmg = attacker.attack(target);
        lastAction = attacker.getName() + " attacked " + target.getName() + " for " + dmg;

        handleExtraTurn(attacker);
    }

    public void playerSkill(int targetIndex) {
        Character attacker = party.get(turnIndex);
        Character target = enemies.get(targetIndex);

        int beforeTurn = turnIndex;

        int dmg = attacker.useSkill(target);

        if (dmg == 0) {
            lastAction = attacker.getName() + " failed to use skill!";
            refreshUI();
            return;
        }

        lastAction = attacker.getName() + " used skill on " + target.getName() + " for " + dmg;

        handleExtraTurn(attacker);
    }

    public void useItem(int index, Character target) {
        Items item = inventory.get(index);
        item.use(target);

        lastAction = target.getName() + " used " + item.getName();
        inventory.remove(index);

        nextTurn();
    }

    private void handleExtraTurn(Character attacker) {
        refreshUI();

        // THIEF stuff
        if (attacker instanceof Thief thief && thief.didTriggerExtraTurn()) {
            lastAction += "\n" + attacker.getName() + " gets another turn!";
            refreshUI();
            return; 
        }

        nextTurn();
    }
    
    public void playerDefend() {

        Character defender = party.get(turnIndex);
        defender.defend();

        lastAction = defender.getName() + " is defending!";

        nextTurn();
    }
    // TURN SYSTEM 

    public void nextTurn() {
        removeDead();

        if (isBattleOver()) {
            
            refreshUI();
            return;
        }

        if (playerTurn) {
            turnIndex++;

            if (turnIndex >= party.size()) {
                turnIndex = 0;
                playerTurn = false;
            }

        } else {
            turnIndex++;

            if (turnIndex >= enemies.size()) {
                turnIndex = 0;
                playerTurn = true;
            }
        }

        applyPassives();
        refreshUI();
    }

    //ENEMY HERE


    private void enemyTurn() {
        Character attacker = enemies.get(turnIndex);

        int targetIndex = (int)(Math.random() * party.size());
        Character target = party.get(targetIndex);

        int dmg = attacker.attack(target);

        lastAction = attacker.getName() + " attacked " + target.getName() + " for " + dmg;
    }

    public void enemyAct() {

        if (playerTurn || isBattleOver()) return;
        if (party.isEmpty()) return;

        Character attacker = enemies.get(turnIndex);

        int targetIndex = (int)(Math.random() * party.size());
        Character target = party.get(targetIndex);

        int dmg = attacker.attack(target);

        lastAction = attacker.getName() + " attacked " + target.getName() + " for " + dmg;

        nextTurn();
    }
    // HELPERS

    private void removeDead() {
        party.removeIf(c -> c.getHp() <= 0);
        enemies.removeIf(c -> c.getHp() <= 0);
    }

    private void applyPassives() {
        for (Character c : party) c.passive();
        for (Character c : enemies) c.passive();
    }

    public boolean isBattleOver() {
        return party.isEmpty() || enemies.isEmpty();
    }

    public boolean addItem(Items item) {

        if (inventory.size() >= 5) {
            lastAction = "Inventory full! (Max 5 items)";
            refreshUI();
            return false; //IF BAD
        }

        inventory.add(item);
        return true; //IF GOOD
    }
    
    public ArrayList<Items> getInventory() {
        return inventory;
    }
    
    
    // Limits (for Items)
    public int countItem(String itemName) {
        int count = 0;
        for (Items i : inventory) {
            if (i.getName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }
}


