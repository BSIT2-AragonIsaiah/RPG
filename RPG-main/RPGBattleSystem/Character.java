/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

/**
 *
 * @author Isaiah Aragon
 */
public abstract class Character{
    
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int mp;
    private int maxMp;

    public Character(String name, int hp, int maxHp, int attack, int defense, int mp, int maxMp) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.mp = mp;
        this.maxMp = maxMp;
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

    public int getMp() {
        return mp;
    }

    public int getMaxMp() {
        return maxMp;
    }
    //setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMp(int mp) {
        this.mp = mp;
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
    
    public void takeHP(int heal){
        
        if(hp != 0) {
            hp += heal;
        }
    }
    
    public void takeMP(int mana){
        
        mp += mana;
    }
    
    public void boost(){
        
        attack *= 2;
        defense *= 2;
    }
    
    
    //enemy movements
    public abstract void attack(Character enemy);
    public abstract void useSkill(Character enemy);
    
    
    //
    public void passiveSkill(Character player){
        
    }
//    public abstract void useHealpot(Character player);
//    public abstract void useUtilpot(Character player);
//    public abstract void useMppot(Character player);
}

