/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

/**
 *
 * @author Isaiah Aragon
 */
public abstract class Character_Sheet{
    
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int cooldown;

    public Character_Sheet(String name, int hp, int maxHp, int attack, int defense, int cooldown) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.cooldown = cooldown;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getCooldown() {
        return cooldown;
    }

    //setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
    
    //commons
    public void takeDamage(int damage){
        int finalDamage = damage - defense;
        
        if(finalDamage < 0) {
            finalDamage = 0;
        }
        
        hp -= finalDamage;
        
        if(hp < 0) {
            hp = 0;
        }
    }
    
    //enemy movements
    public abstract void attack(Character_Sheet enemy);
    public abstract void useSkill(Character_Sheet enemy);
}

