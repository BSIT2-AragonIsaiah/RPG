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
public class Normie extends Character {
// TESTING DUMMY
    
    static {
        CharacterFactory.register("Normie", Normie::new);
    }
    
    private int dodgeTurns = 2;

    public Normie(String name) {
        super(name, 999, 999, 999, 999);
    }

    @Override
    public int attack(Character enemy) {
        int beforeHp = enemy.getHp();
        enemy.takeDamage(getAttack());
        return beforeHp - enemy.getHp();
    }

    
    // Skill: Double Shot (costs 20 MP)
    @Override
    public int useSkill(Character enemy) {
        int cost = 1;

        if (getMp() < cost) {
            JOptionPane.showMessageDialog(null, "Not enough MP!");
            return 0;
        }

        useMana(cost);

        int beforeHp = enemy.getHp();
        enemy.takeDamage(getAttack() * 99);
        return beforeHp - enemy.getHp();
    }


    @Override
    public void passive() {
         restoreMana(999);
    }
    
}
