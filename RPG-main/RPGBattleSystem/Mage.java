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

    public Mage(String name, int hp, int maxHp, int attack, int defense, int mp, int maxMp) {
        super(name, 30, 30, 4, 2, 100, 100);
    }
    @Override
    public void attack(Character enemy) {

        enemy.takeDamage(getAttack());

    }
    @Override
    public void useSkill(Character enemy) {

        if(getMp() <= 40) {

            int magicDamage = getAttack() * 3;

            enemy.takeDamage(magicDamage);

            setMp(getMp() - 40);
        }
    }
}
