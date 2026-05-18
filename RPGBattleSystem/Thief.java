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
public class Thief extends Character {

    
    static {
        CharacterFactory.register("Thief", Thief::new);
    }
    
    private boolean extraTurn = false;

    public Thief(String name) {
        super(name, 30, 60, 3, 3);
    }

    @Override
    public int attack(Character enemy) {
        int damage = getAttack();

        int beforeHp = enemy.getHp();
        enemy.takeDamage(damage);
        int finalDamage = beforeHp - enemy.getHp();

        triggerPassive();

        return finalDamage;
    }

    private void triggerPassive() {
        extraTurn = Math.random() < 0.3;
    }

    public boolean didTriggerExtraTurn() {
        boolean result = extraTurn;
        extraTurn = false;
        return result;
    }

    //Staby 
    @Override
    public int useSkill(Character enemy) {
        int cost = 30;

        if (getMp() >= cost) {
            useMana(cost);

            int beforeHp = enemy.getHp();
            enemy.takeTrueDamage(getAttack() * 3);
            int finalDamage = beforeHp - enemy.getHp();

            extraTurn = true; // guaranteed

            return finalDamage;
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Not enough MP!");
            return 0;
        }
    }

    @Override
    public void passive() { restoreMana(2); }
    
    @Override
    public String getImagePath() {
        return "/RPGBattleSystem/assets/thief.png";
    }
}
