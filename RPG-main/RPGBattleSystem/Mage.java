/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

/**
 *
 * @author Isaiah Aragon
 */
public class Mage extends Character{

    public Mage(String name, int hp, int maxHp, int attack, int defense, int cooldown) {
        super(name, 10, 10, 2, 2, 0);
    }
    @Override
    public void attack(Character enemy) {

        enemy.takeDamage(getAttack());

    }

    @Override
    public void useSkill(Character enemy) {

        if(getCooldown() == 0) {

            int magicDamage = getAttack() + 3;

            enemy.takeDamage(magicDamage);

            setCooldown(3);
        }
    }
}
