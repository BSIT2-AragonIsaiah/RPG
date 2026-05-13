/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

/**
 *
 * @author Isaiah Aragon
 */
public class Bulwark extends Character{
    public Bulwark(String name, int hp, int maxHp, int attack, int defense, int cooldown) {
        super(name, 10, 10, 1, 4, 0);
    }
    @Override
    public void attack(Character enemy) {

        enemy.takeDamage(getAttack());

    }

    @Override ///COUNTER (DEF*2 ==> Attack)
    public void useSkill(Character enemy) {

        if(getCooldown() == 0) {

            int counter = getDefense() * 2;

            enemy.takeDamage(counter);

            setCooldown(2);
        }
    }
}
