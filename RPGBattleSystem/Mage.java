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
public class Mage extends Character {

    
    static {
        CharacterFactory.register("Mage", Mage::new);
    }
            
    public Mage(String name) {
        super(name, 30, 100, 6, 1);
    }

    @Override
    public int attack(Character enemy) {
        int damage = getAttack();
        
        int beforeHp = enemy.getHp();
        enemy.takeDamage(damage);
        int finalDamage = beforeHp - enemy.getHp();
        
        return finalDamage;
    }

    // Skill: Fireball (high damage, costs 80 MP)
    @Override
    public int useSkill(Character enemy) {

        int cost = 80;

        if (getMp() >= cost) {

            int magicDamage = getAttack() * 4;
            
            int beforeHp = enemy.getHp();
            enemy.takeDamage(magicDamage);
            int finalDamage = beforeHp - enemy.getHp();
            
            useMana(cost);

            return finalDamage;
        } else {
            JOptionPane.showMessageDialog(null, "Not enough MP!");
            return 0;
        }
    }

    // Passive: More Regenerate extra MP each turn
    @Override
    public void passive() {
        restoreMana(8);
    }
    @Override
    public String getImagePath() {
        return "/RPGBattleSystem/assets/mage.png";
    }
}
