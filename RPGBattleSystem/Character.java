/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Isaiah Aragon
 */
public abstract class Character implements CombatActions {

    private String name;

    private int hp, maxHp;
    private int mp, maxMp;

    private int attack, defense;

    private boolean defending = false;
    private ArrayList<Items> inventory = new ArrayList<>();

    // CONSTRUCTOR
    public Character(String name, int maxHp, int maxMp, int attack, int defense) {
        this.name = name;

        this.maxHp = maxHp;
        this.hp = maxHp;

        this.maxMp = maxMp;
        this.mp = maxMp;

        this.attack = attack;
        this.defense = defense;
    }

    // GETTERS
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getMp() { return mp; }
    public int getMaxMp() { return maxMp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }

    // CORE METHODS
    public void takeDamage(int damage) {
        if (defending) {
            damage /= 2;
            defending = false;
        }

        int finalDamage = (int) (damage * (1.0 - (defense / 100.0)));
        if (finalDamage < 0) finalDamage = 0;

        hp -= finalDamage;
        if (hp < 0) hp = 0;
    }

    public void takeTrueDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) hp = maxHp;
    }

    public void useMana(int amount) {
        mp -= amount;
        if (mp < 0) mp = 0;
    }

    public void restoreMana(int amount) {
        mp += amount;
        if (mp > maxMp) mp = maxMp;
    }

    public void increaseAttack(int amount) { attack += amount; }
    public void increaseDefense(int amount) { defense += amount; }

    // INVENTORY
    public void addItem(Items item) {
        inventory.add(item);
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public void useItem(int index, Character target) {
        if (inventory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No items!");
            return;
        }

        Items item = inventory.get(index);
        item.use(target);

        inventory.remove(index);
    }

    // DEFEND SYSTEM
    public void defend() {
        defending = true;
    }

    public boolean isDefending() {
        return defending;
    }

    public void resetDefend() {
        defending = false;
    }
    
    //PROOFING
    public boolean isHealer() {
        return false;
    }

    //HELPER
    public String getImagePath() {
        return "/RPGBattleSystem/assets/shop.png";
    }
    // ABSTRACT COMBAT METHODS
    @Override
    public abstract int attack(Character target);

    @Override
    public abstract int useSkill(Character target);

    @Override
    public abstract void passive();
}