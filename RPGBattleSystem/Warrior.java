/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

import javax.swing.JOptionPane;

/**
 *
 * @author Isaiah Aragon
 */
public class Warrior extends Character {

    
    static {
        CharacterFactory.register("Warrior", Warrior::new);
    }
    
    public Warrior(String name) {
        super(name, 50, 30, 4, 4);
    }

    @Override
    public int attack(Character enemy) {
        int beforeHp = enemy.getHp();
        enemy.takeDamage(getAttack());
        return beforeHp - enemy.getHp();
    }

    
    // Skill: Power Strike (costs 10 MP)
    @Override
    public int useSkill(Character enemy) {
        int cost = 10;

        if (getMp() < cost) {
            JOptionPane.showMessageDialog(null, "Not enough MP!");
            return 0;
        }

        useMana(cost);

        int beforeHp = enemy.getHp();
        enemy.takeDamage(getAttack() * 2);
        return beforeHp - enemy.getHp();
    }

    @Override
    public void passive() {
        if (getHp() <= getMaxHp() / 2) {
            increaseAttack(2);
        }
        restoreMana(2);
    }
    
    @Override
    public String getImagePath() {
        return "/RPGBattleSystem/assets/warrior.png";
    }
}
