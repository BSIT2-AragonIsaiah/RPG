/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

/**
 *
 * @author Isaiah Aragon
 */

//Refer to this character
public class Archer extends Character{
    
    public Archer(String name, int hp, int maxHp, int attack, int defense, int mp, int maxMp) {
        super(name, 20, 20, 5, 3, 50, 50);
    }
    @Override
    public void attack(Character enemy) {

        enemy.takeDamage(getAttack());

    }
    //SHOOT
    
    @Override
    public void useSkill(Character enemy) {

        if(getMp() <= 20) {

            int skillDamage = getAttack() + 3;

            enemy.takeDamage(skillDamage);

            setMp(getMp() - 20);
        }
    }
    
    //Passive - Avoid taking damage for the first 2 turns
    @Override
    public void passiveSkill(Character player){
        player.takeDamage(0);
    }
}
