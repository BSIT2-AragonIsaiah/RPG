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
public class Bulwark extends Character {

    
    static {
        CharacterFactory.register("Bulwark", Bulwark::new);
    }
    
    private boolean counterReady = false;

    public Bulwark(String name) {
        super(name, 60, 50, 3, 10);
    }

    @Override
    public int attack(Character enemy) {
        int beforeHp = enemy.getHp();
        enemy.takeDamage(getAttack());
        return beforeHp - enemy.getHp();
    }

    // Skill: Bide Stance (cost 40 MP)
    @Override
    public int useSkill(Character enemy) {
        int cost = 40;

        if (getMp() < cost) {
            JOptionPane.showMessageDialog(null, "Not enough MP!");
            return 0;
        }

        useMana(cost);
        counterReady = true;

        return 0;
    }

    @Override
    public void takeDamage(int damage) {
        if (counterReady) {
            damage /= 2;

            JOptionPane.showMessageDialog(null,
                getName() + " blocked and reduced damage!"
            );

            counterReady = false;
        }

        super.takeDamage(damage);
    }

    //Bolster when health is low
    @Override
    public void passive() {
        if (getHp() <= getMaxHp() / 6) {
            increaseDefense(3);
        }
         restoreMana(2);
    }
    
    @Override
    public String getImagePath() {
        return "/RPGBattleSystem/assets/bulwark.png";
    }
}
